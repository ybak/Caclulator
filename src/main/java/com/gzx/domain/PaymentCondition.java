package com.gzx.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 5/21/2017.
 */
public class PaymentCondition {
    /**
     * 借出日期
     */
    @NotNull(message = "借出日期不能为空！")
    protected String loanDate;
    /**
     * 借出金额
     */
    @Min(value = 0, message = "借出金额必须大于0！")
    protected Double loanAmount;
    /**
     * 还款期限
     */
    @Min(value = 0, message = "还款期限必须大于0！")
    protected int loanTerm;
    /**
     * 期限方式（月、天），每月算30天，一年算360天：1->月;2->天
     */
    @Min(value = 1, message = "期限方式错误！")
    @Max(value = 2, message = "期限方式错误！")
    protected int loanTermType;
    /**
     * 利率
     */
    @Min(value = 0, message = "利率必须大于0！")
    protected Double loanRate;

    /**
     * 利率方式：1->年化率；2->日化率
     */
    @Min(value = 1, message = "利率方式错误！")
    @Max(value = 2, message = "利率方式错误！")
    protected int loanRateType;

    /**
     * 还款方式
     */
    @Min(value = 1, message = "还款方式错误！")
    @Max(value = 8, message = "还款方式错误！")
    protected int repayType;

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public int getLoanTermType() {
        return loanTermType;
    }

    public void setLoanTermType(int loanTermType) {
        this.loanTermType = loanTermType;
    }

    public Double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Double loanRate) {
        this.loanRate = loanRate;
    }

    public int getLoanRateType() {
        return loanRateType;
    }

    public void setLoanRateType(int loanRateType) {
        this.loanRateType = loanRateType;
    }

    public int getRepayType() {
        return repayType;
    }

    public void setRepayType(int repayType) {
        this.repayType = repayType;
    }
}
