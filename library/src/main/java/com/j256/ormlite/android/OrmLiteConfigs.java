package com.j256.ormlite.android;

/**
 * created by 103style  2019/5/8 22:25
 */
public class OrmLiteConfigs {

    private static String secret;

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        OrmLiteConfigs.secret = secret;
    }
}
