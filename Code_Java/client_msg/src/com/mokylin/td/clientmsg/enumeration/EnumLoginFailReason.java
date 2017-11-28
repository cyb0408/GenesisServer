// Generated by the SocketProtoGenerationTool.  DO NOT EDIT!
package com.mokylin.td.clientmsg.enumeration;

//
public enum EnumLoginFailReason {


    // 版本与服务器不一致
    VERSION_ERROR(0),


    // 渠道不存在
    CHANNEL_NOT_EXIST(1),


    // 账号包含不合法字符
    ACCOUNT_NOT_ALLOW(2),


    // 服务器尚未开放
    NOT_OPEN(3),


    // 服务器人数已满
    FULL(4),


    // 账号未激活
    ACCOUNT_NOT_ACTIVE(5),


    // 验证失败
    AUTH_ERROR(6),


    // 加载角色失败
    LOAD_ROLE_FAIL(7);

    private final int value;

    EnumLoginFailReason(int value) {
        this.value = value;
    }

    public static EnumLoginFailReason valueOf(int _value) {
        for (EnumLoginFailReason each : EnumLoginFailReason.values()) {
            if (each.value == _value) {
                return each;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}

		