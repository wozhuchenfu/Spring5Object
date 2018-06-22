package com.qi.exceptionUtils;

public enum BizError {
    SYSTEM_ERROR("000","系统异常"),
    PARAMETER_INVALID_ERROR("001","非法参数"),
    NOT_PERMISSION_ERROR("002","没有操作权限");
    BizError(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
    private final String errorCode;
    private final String errorDesc;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

}
