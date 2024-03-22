package com.lm.lmliving.commodity.exception;

import com.lm.lmliving.common.exception.LmlivingCodeEnum;
import com.lm.lmliving.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * 1.@ControllerAdvice(basePackages = "com.lm.lmliving.commodity.controller")
 * 表示这是一个全局异常处理类 如果指定包下的controller发生了异常就会走到这里，不指定包就表示处理所有的controller
 * 2.basePackages = "com.lm.lmliving.commodity.controller"表示要处理com.lm.lmliving.commodity.controller下的controller类
 */
@ResponseBody
@Slf4j
@ControllerAdvice(basePackages = "com.lm.lmliving.commodity.controller")
public class LmlivingExceptionControllerAdvice {
    /**
     * 1.@ExceptionHandler({MethodArgumentNotValidException.class})表示如果是MethodArgumentNotValidException异常，
     * 就交给这个方法(handleValidException())处理
     * 2.数据校验失败，会抛出的异常是 MethodArgumentNotValidException.class
     * 3.参数传入MethodArgumentNotValidException e，方便获取异常信息
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R handleValidException(MethodArgumentNotValidException e) {
        // 得到BindingResult
        BindingResult bindingResult = e.getBindingResult();

        // 创建map，用于存放错误信息
        Map<String, String> errorMap = new HashMap<>();
        // 遍历result,将错误信息收集到map
        bindingResult.getFieldErrors().forEach((item) -> {
            // 得到filed
            String field = item.getField();
            // 得到校验错误信息
            String defaultMessage = item.getDefaultMessage();
            // 将信息放入map
            errorMap.put(field, defaultMessage);
        });
        return R.error(LmlivingCodeEnum.INVALID_EXCEPTION.getCode(), LmlivingCodeEnum.INVALID_EXCEPTION.getMsg()).put("data", errorMap);

    }

    //说明：如果按照业务逻辑，需要去精确匹配处理异常/错误，可以再单独写方法
    /**
     * 编写方法，处理没有精确匹配到的异常
     * 返回一个统一的信息，方便前端处理
     */
    @ExceptionHandler({Throwable.class})
    public R handleException (Throwable throwable) {
        return R.error(LmlivingCodeEnum.UNKNOWN_EXCEPTION.getCode(),LmlivingCodeEnum.UNKNOWN_EXCEPTION.getMsg()).put("data",throwable.getMessage());
    }
}
