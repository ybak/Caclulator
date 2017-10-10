package com.gzx.Util;

import com.gzx.domain.Result;

/**
 * http请求返回的响应
 * Created by Administrator on 5/22/2017.
 */
public class ResultUtil {
    //http请求返回的的成功响应（成功代码，提示信息，数据）
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("请求成功！");
        result.setScheduleList(object);
        return result;
    }

    //http请求返回的的错误响应（错误代码，提示信息）
    public static Result error(Integer code, String errMsg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(errMsg);
        return result;
    }
}
