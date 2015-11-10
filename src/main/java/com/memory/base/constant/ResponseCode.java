package com.memory.base.constant;

/**
 * response code
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 19:04
 */
public enum ResponseCode {

    REDIRECT(700, "重定向"),
    SUCCESS(701, "成功"),
    FAILURE(702, "失败"),
    ERROR(703, "内部错误"),
    PARAM_ERROR(704, "参数错误"),
    PASSWORD_ERROR(705, "密码错误"),
    CAPTCHA_ERROR(706, "校验码错误"),
    CONFIRM_PASSWORD_ERROR(707, "确认密码错误"),
    NOT_FOUND_ACCOUNT(708, "账号不存在"),
    INVALID_ACCOUNT(709, "无效账号"),
    IS_FIRST_LOGIN(710, "首次登陆"),
    TO_MANY_ERR_INPUT(711, "登陆密码错误输入超过五次"),
    INVALID_IP(712, "IP登录受限"),

    X(999, "内部错误");

    private final int code;
    private final String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
