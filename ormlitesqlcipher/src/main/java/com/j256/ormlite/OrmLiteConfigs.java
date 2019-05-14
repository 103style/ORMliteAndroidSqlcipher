package com.j256.ormlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * @author create by 103style  2019/5/8 22:25
 */
public class OrmLiteConfigs {

    /**
     * name of SharedPreferences
     */
    private static final String SP_NAME = "ormlite_sqlcipher_sp_name";
    /**
     * the key to save the db password in SharedPreferences
     */
    private static final String ORMLITE_SQLCIPHER_PASSWORD = "ormlite_sqlcipher_password";

    /**
     * single instance
     */
    @SuppressLint("StaticFieldLeak")
    private static OrmLiteConfigs instance;
    /**
     * the default db password
     */
    private final String DEFAULT_DB_PASSWORD = "ormlite_sqlcipher";
    /**
     * your db password
     */
    private String dbPassword;
    /**
     * application context
     */
    private Context mContext;

    private OrmLiteConfigs() {
    }

    public static OrmLiteConfigs getInstance() {
        if (instance == null) {
            synchronized (OrmLiteConfigs.class) {
                if (instance == null) {
                    instance = new OrmLiteConfigs();
                }
            }
        }
        return instance;
    }

    /**
     * init
     *
     * @param context    context
     * @param dbPassword your db password
     */
    public void init(Context context, String dbPassword) {
        mContext = context.getApplicationContext();
        this.dbPassword = dbPassword;
        SQLiteDatabase.loadLibs(context);
        setSecret(dbPassword);
    }

    /**
     * get the db password
     */
    public String getSecret() {
        if (mContext == null) {
            throw new IllegalStateException("you must call OrmLiteConfigs.getInstance.init(Context context, String dbPassword) first in your application onCreate");
        }
        if (TextUtils.isEmpty(dbPassword)) {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            dbPassword = sharedPreferences.getString(ORMLITE_SQLCIPHER_PASSWORD, DEFAULT_DB_PASSWORD);
        }
        return dbPassword;
    }

    /**
     * set and save db password
     */
    private void setSecret(String dbPassword) {
        this.dbPassword = dbPassword;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ORMLITE_SQLCIPHER_PASSWORD, dbPassword);
        editor.apply();
    }
}
