package com.gzx.calculator;

import com.gzx.domain.*;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算
 * 每月还息，到期还本
 */
public class CalcMonthInFinPr extends Calculator {
    //调用父类方法
    public CalcMonthInFinPr(PaymentCondition paymentCondition) {
        super(paymentCondition);
    }

    private final static Logger logger = Logger.getLogger(CalcMonthInFinPr.class);

    public List<PaymentSchedule> Calc() {
        List<PaymentSchedule> scheduleList = new ArrayList<>();

        //获取利息
        double interestMonth;
        double balance;
        DateTime dateTimeMonth;
        //期限方式为月
        if (Constant.MONTHLY == loanTermType){
            dateTimeMonth = dateTime.plusDays(loanTermType * 30);
            for(int i = (int)loanTerm; i > 0; i--){
                PaymentSchedule paymentSchedule = new PaymentSchedule();
                interestMonth = interestSum.divide(loanTermMonth,3, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (i < (int)loanTerm){
                    dateTimeMonth = dateTimeMonth.plusDays(30);
                }
                paymentSchedule.setPayDate(dateTimeMonth);
                paymentSchedule.setInterest(interestMonth);
                paymentSchedule.setPrincipal(loanAmount);
                balance = interestMonth;
                if (i==1)
                    balance += loanAmount;
                paymentSchedule.setBalance(balance);
                logger.info(i+" "+"paymentSchedule："+paymentSchedule);
                scheduleList.add(paymentSchedule);
            }
        }
        //期限方式为日
        else {
            //期限大于30天
            if (loanTerm > 30){
                //首月、中间月份
                double interestFirstMonth;
                double interestLastMonth;
                interestFirstMonth = calcLoanAmount.multiply(month).multiply(loanRateDay).doubleValue();
                int remainDay;
                for (remainDay = (int)loanTerm;remainDay > 30;remainDay -= 30){
                    PaymentSchedule paymentSchedule = new PaymentSchedule();
                    dateTimeMonth = dateTime.plusDays(30);
                    paymentSchedule.setPayDate(dateTimeMonth);
                    paymentSchedule.setInterest(interestFirstMonth);
                    paymentSchedule.setPrincipal(loanAmount);
                    balance = interestFirstMonth;
                    paymentSchedule.setBalance(balance);
                    logger.info("paymentSchedule："+paymentSchedule);
                    scheduleList.add(paymentSchedule);
                }
                //末月
                PaymentSchedule paymentSchedule = new PaymentSchedule();
                BigDecimal calcRemainDay = new BigDecimal(remainDay).setScale(3, BigDecimal.ROUND_HALF_UP);
                interestLastMonth = calcLoanAmount.multiply(calcRemainDay).multiply(loanRateDay).doubleValue();
                dateTimeMonth = dateTime.plusDays(remainDay);
                paymentSchedule.setPayDate(dateTimeMonth);
                paymentSchedule.setInterest(interestLastMonth);
                paymentSchedule.setPrincipal(loanAmount);
                balance = interestFirstMonth + loanAmount;
                paymentSchedule.setBalance(balance);
                logger.info("paymentSchedule："+paymentSchedule);
                scheduleList.add(paymentSchedule);
            }
            //期限小于30天
            else {
                interestMonth = interestSum.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                PaymentSchedule paymentSchedule = new PaymentSchedule();
                dateTimeMonth = dateTime.plusDays((int)loanTerm);
                paymentSchedule.setPayDate(dateTimeMonth);
                paymentSchedule.setInterest(interestMonth);
                paymentSchedule.setPrincipal(loanAmount);
                balance = interestMonth + loanAmount;
                paymentSchedule.setBalance(balance);
                logger.info("paymentSchedule："+paymentSchedule);
                scheduleList.add(paymentSchedule);
            }

        }

        return scheduleList;
    }

}
