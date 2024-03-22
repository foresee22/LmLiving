package com.lm.lmliving.common.exception;

import lombok.Getter;

@Getter
public enum LmlivingCodeEnum {
    UNKNOWN_EXCEPTION(40000,"系统未知异常"),
    INVALID_EXCEPTION(40001,"参数校验异常");
    private int code;
    private String msg;

    LmlivingCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
