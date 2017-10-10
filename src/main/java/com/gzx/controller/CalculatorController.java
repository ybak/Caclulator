package com.gzx.controller;

import com.gzx.Util.ResultUtil;
import com.gzx.calculator.*;
import com.gzx.domain.PaymentCondition;
import com.gzx.domain.PaymentSchedule;
import com.gzx.domain.Result;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 计算控制器
 * Created by user on 2017/5/22.
 */
@RestController
public class CalculatorController {

    private final static Logger logger = Logger.getLogger(CalculatorController.class);
    //接受http请求
    @PostMapping(value = "/calc")
    public Result paymentConditionAdd(@Valid PaymentCondition paymentCondition, BindingResult bindingResult){
        //验证接受的数据
        if (bindingResult.hasErrors()){
            //http请求返回的的错误响应（错误代码，提示信息）
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        List<PaymentSchedule> scheduleList;
        int repayType = paymentCondition.getRepayType();
        logger.info("repayType:"+repayType);
        //选择计算方法
        switch (repayType){
            case 1:
                CalcFinAll calcFinAll = new CalcFinAll(paymentCondition);
                scheduleList = calcFinAll.Calc();
                break;
            case 2:
                CalcMonthInFinPr calcMonthInFinPr = new CalcMonthInFinPr(paymentCondition);
                scheduleList = calcMonthInFinPr.Calc();
                break;
            default:
                scheduleList = null;
        }
        //http请求返回的的成功响应（成功代码，提示信息，数据）
        return ResultUtil.success(scheduleList);
    }
}
