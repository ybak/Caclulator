package com.gzx.calculator;

import com.gzx.domain.*;
import com.gzx.domain.PaymentCondition;
import com.gzx.domain.PaymentSchedule;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算
 * 到期还本息
 */
public class CalcFinAll extends Calculator {
    //调用父类方法
    public CalcFinAll(PaymentCondition paymentCondition) {
        super(paymentCondition);
    }
    //Log4j
    private final static Logger logger = Logger.getLogger(CalcFinAll.class);

    public List<PaymentSchedule> Calc() {

        List<PaymentSchedule> scheduleList = new ArrayList<>();
        PaymentSchedule paymentSchedule = new PaymentSchedule();

        double interest = interestSum.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        double balance = interest + loanAmount;

        paymentSchedule.setPayDate(loanTermType == 1 ? dateTime.plusDays((int)loanTerm * 30) : dateTime.plusDays((int)loanTerm));
        paymentSchedule.setInterest(interest);
        paymentSchedule.setPrincipal(loanAmount);
        paymentSchedule.setBalance(balance);
        logger.info("paymentSchedule："+paymentSchedule);
        scheduleList.add(paymentSchedule);
        return scheduleList;
    }

}
