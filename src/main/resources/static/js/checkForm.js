/**
 * Created by Administrator on 5/25/2017.
 */
//检查借出金额
function loanAmountUp(){
    //传入值
    var loanAmountValue = document.getElementById("loanAmount").value;
    //判断是否可以转换为数字
    var loanAmountIsNaN = isNaN(parseFloat(loanAmountValue));
    //判断是否合法
    var loanAmountPatt = /^(([1-9]\d{0,12})|[0])(\.\d{1,3})?$/g;
    var loanAmountIsValid = loanAmountPatt.test(loanAmountValue);
    //若可以转换为数字且合法
    if (loanAmountIsNaN == false && loanAmountIsValid == true){
        console.log("Is valid number : "+loanAmountValue);
    }
    //若不可转换为数字或不合法
    else {
        //若可转换为数字但不合法
        if (loanAmountIsNaN == false && loanAmountIsValid == false){
            console.log("Is not valid number : "+loanAmountValue);
        }
        //若不可转换为数字
        else {
            console.log("Is not a number : "+loanAmountValue);
        }
        //统一合法化处理
        //去除非0-9和.字符
        loanAmountValue = loanAmountValue.replace(/[^\.\d]/g, "");
        //将整数部分开始的0
        if (loanAmountValue.match(/^[0]\d/)){
            loanAmountValue = loanAmountValue.replace(/^[0]\d/, loanAmountValue.substring(1));
        }
        //整数部分转换为13位
        if (loanAmountValue.match(/^\d{14}/)){
            loanAmountValue = loanAmountValue.replace(loanAmountValue, loanAmountValue.substring(0, 13));
        }
        //将连续的.替换为1个
        if (loanAmountValue.match(/\.{2,}/g)){
            loanAmountValue = loanAmountValue.replace(/\.{2,}/g, ".");
        }
        //去除多余的. 控制小数部分位数
        if (loanAmountValue.match(/^\d{1,13}\.\d{1,}\.?/)){
            loanAmountValue = loanAmountValue.replace(loanAmountValue, loanAmountValue.match(/^\d{1,13}\.\d{1,2}/));
            console.log("去除多余的.");
        }
    }
    //返回浮点类型
    loanAmount.value = loanAmountValue;
    console.log("  Return : "+loanAmount.value);
}
//检查利率输入
function loanRateUp(){
    //传入值
    var loanRateValue = document.getElementById("loanRate").value;
    //判断是否可以转换为数字
    var loanRateIsNaN = isNaN(parseFloat(loanRateValue));
    //判断是否合法
    var loanRatePatt = /^(([1-9]\d?)|[0])(\.\d{1,3})?$/g;
    var loanRateIsValid = loanRatePatt.test(loanRateValue);
    //若可以转换为数字且合法
    if (loanRateIsNaN == false && loanRateIsValid == true){
        console.log("Is valid number : "+loanRateValue);
    }
    //若不可转换为数字或不合法
    else {
        //若可转换为数字但不合法
        if (loanRateIsNaN == false && loanRateIsValid == false){
            console.log("Is not valid number : "+loanRateValue);
        }
        //若不可转换为数字
        else {
            console.log("Is not a number : "+loanRateValue);
        }
        //统一合法化处理
        //去除非0-9和.字符
        loanRateValue = loanRateValue.replace(/[^\.\d]/g, "");
        //将整数部分开始的0
        if (loanRateValue.match(/^[0]\d/)){
            loanRateValue = loanRateValue.replace(/^[0]\d/, loanRateValue.substring(1));
        }
        //整数部分转换为2位
        if (loanRateValue.match(/^\d{3}/)){
            loanRateValue = loanRateValue.replace(loanRateValue, loanRateValue.substring(0, 2));
        }
        //将连续的.替换为1个
        if (loanRateValue.match(/\.{2,}/g)){
            loanRateValue = loanRateValue.replace(/\.{2,}/g, ".");
        }
        //去除多余的.
        if (loanRateValue.match(/^\d{1,2}\.\d{1,}\.?/)){
            loanRateValue = loanRateValue.replace(loanRateValue, loanRateValue.match(/^\d{1,2}\.\d{1,3}/));
        }
    }
    //返回浮点类型
    loanRate.value = loanRateValue;
    console.log("  Return : "+loanRate.value);
}

