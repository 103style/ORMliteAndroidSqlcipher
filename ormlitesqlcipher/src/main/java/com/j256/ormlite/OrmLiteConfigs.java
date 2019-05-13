package com.j256.ormlite;

/**
 * created by 103style  2019/5/8 22:25
 */
public class OrmLiteConfigs {

    /**
     * 配置你的数据库密码
     */
    private static String secret = "update_your_secret";

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        OrmLiteConfigs.secret = secret;
    }
}
