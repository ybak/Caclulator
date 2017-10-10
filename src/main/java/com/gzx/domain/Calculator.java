package com.gzx.domain;

import org.joda.time.DateTime;
import java.math.BigDecimal;
import java.util.List;

/**
 * 还款计算，抽象父类
 */
public abstract class Calculator {

    protected String loanDate;
    protected double loanAmount;
    protected double loanTerm;
    protected int loanTermType;
    protected double loanRate;
    protected int loanRateType;
    protected DateTime dateTime = new DateTime(loanDate);
    protected BigDecimal loanTermDay;
    protected BigDecimal loanTermMonth;
    protected BigDecimal calcLoanAmount;
    protected BigDecimal calcLoanRate;
    protected BigDecimal loanRateDay;
    protected BigDecimal loanRateMonth;
    protected BigDecimal interestSum;
    protected BigDecimal month = new BigDecimal(30).setScale(3, BigDecimal.ROUND_HALF_UP);
    protected BigDecimal monthNumber = new BigDecimal(12).setScale(3, BigDecimal.ROUND_HALF_UP);
    protected BigDecimal year = new BigDecimal(360).setScale(3, BigDecimal.ROUND_HALF_UP);

    protected Calculator(PaymentCondition paymentCondition) {
        this.loanDate = paymentCondition.getLoanDate();
        this.loanAmount = paymentCondition.getLoanAmount();
        this.loanTerm = paymentCondition.getLoanTerm();
        this.loanTermType = paymentCondition.getLoanTermType();
        this.loanRate = paymentCondition.getLoanRate();
        this.loanRateType = paymentCondition.getLoanRateType();
        //转换本金和利率为BigDecimal
        calcLoanAmount = new BigDecimal(loanAmount).setScale(3, BigDecimal.ROUND_HALF_UP);
        calcLoanRate = new BigDecimal(loanRate / 100).setScale(5, BigDecimal.ROUND_HALF_UP);
        //转换期限和利率为对应关系
        //期限为日
        if (Constant.DAILY == loanTermType) {
            loanTermDay = new BigDecimal(loanTerm).setScale(3, BigDecimal.ROUND_HALF_UP);
            //日利率
            if (Constant.DAILYRATE == loanRateType)
                loanRateDay = calcLoanRate;
            //年利率
            else
                loanRateDay = calcLoanRate.divide(year,5, BigDecimal.ROUND_HALF_UP);
            //利息和
            interestSum = calcLoanAmount.multiply(loanRateDay).multiply(loanTermDay);
        }
        //期限为月
        else {
            //日利率
            if (Constant.DAILYRATE == loanRateType) {
                loanTermMonth = new BigDecimal(loanTerm).setScale(3, BigDecimal.ROUND_HALF_UP);
                loanTermDay = new BigDecimal(loanTerm * 30).setScale(3, BigDecimal.ROUND_HALF_UP);
                loanRateDay = calcLoanRate;
                interestSum = calcLoanAmount.multiply(loanRateDay).multiply(loanTermDay);
            }
            //年利率
            else {
                loanTermMonth = new BigDecimal(loanTerm).setScale(3, BigDecimal.ROUND_HALF_UP);
                loanRateMonth = calcLoanRate.divide(monthNumber,3, BigDecimal.ROUND_HALF_UP);
                interestSum = calcLoanAmount.multiply(loanTermMonth).multiply(loanRateMonth);
            }
        }
    }

    public abstract List<PaymentSchedule> Calc();

}
