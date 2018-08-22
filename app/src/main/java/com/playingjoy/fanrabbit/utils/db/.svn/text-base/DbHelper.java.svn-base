package com.playingjoy.fanrabbit.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.playingjoy.fanrabbit.gen.DaoMaster;
import com.playingjoy.fanrabbit.gen.DaoSession;

/**
 * Author: Ly
 * Data：2018/4/12-10:43
 * Description:  数据库初始化类
 */
public class DbHelper {
    private static DaoMaster.DevOpenHelper helper;
    private static SQLiteDatabase db;
    private static DaoSession mDaoSession;
    private static DaoMaster daoMaster;

    /**
     * 初始化greenDao，这个操作建议在Application初始化的时候添加；
     */
    public static void initDatabase(Context context) {
        helper = new DaoMaster.DevOpenHelper(context, "catPlay.db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }
}
