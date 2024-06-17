package com.rgzn.zt.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rgzn.zt.ActionDetailsActivity;
import com.rgzn.zt.entity.ActionInfo;
import com.rgzn.zt.entity.PlanInfo;

import java.util.ArrayList;
import java.util.List;

public class PlanDbHelper extends SQLiteOpenHelper {

    private static PlanDbHelper sHelper;
    private static final String DB_NAME = "plan.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public PlanDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static PlanDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new PlanDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("PlanDbHelper", "onCreate called1");
        // 创建plan_table表
        db.execSQL("create table plan_table(_id integer primary key autoincrement, " +
                "username text, " +
                "action_id integer, " +
                "action_img integer, " +
                "action_title text, " +
                "action_timeState integer, " +
                "action_count integer" +
                ")");
        Log.d("PlanDbHelper", "onCreate called");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //添加到计划
    public int addPlan(String username, int action_id, int action_img, String action_title, int action_timeState) {
        //如果添加过这个计划，只要增加这个计划的数量
        PlanInfo addPlan = isAddPlan(username, action_id);
        if (addPlan == null) {
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            //填充占位符
            values.put("username", username);
            values.put("action_id", action_id);
            values.put("action_img", action_img);
            values.put("action_title", action_title);
            values.put("action_timeState", action_timeState);
            values.put("action_count", 1);
            String nullColumnHack = "values(null,?,?,?,?,?,?)";
//            String nullColumnHack = null;
            //执行
            int insert = (int) db.insert("plan_table", nullColumnHack, values);
            db.close();
            return insert;
        } else {
            return updateAction(addPlan.getPlan_id(), addPlan);
        }
    }

    //修改plan
    public int updateAction(int plan_id, PlanInfo planInfo) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("action_count", planInfo.getAction_count()+1);
        // 执行SQL
        int update = db.update("plan_table", values, " _id=?", new String[]{plan_id+""});
        // 关闭数据库连接
        db.close();
        return update;

    }

    //修改plan
    public int subupdateAction(int plan_id, PlanInfo planInfo) {
        if (planInfo.getAction_count() > 1) {
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            // 填充占位符
            ContentValues values = new ContentValues();
            values.put("action_count", planInfo.getAction_count()-1);
            // 执行SQL
            int update = db.update("plan_table", values, " _id=?", new String[]{plan_id+""});
            // 关闭数据库连接
            db.close();
            return update;
        }
        else {
            return delete(plan_id+"");
        }
    }

    //根据用户名和action_id判断也没有添加过
    @SuppressLint("Range")
    public PlanInfo isAddPlan(String username, int action_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        PlanInfo planInfo = null;
        String sql = "select _id,username,action_id,action_img,action_title,action_timeState,action_count from plan_table where username=? and action_id=?";
        String[] selectionArgs = {username, action_id+""};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int plan_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int actionId = cursor.getInt(cursor.getColumnIndex("action_id"));
            int action_img = cursor.getInt(cursor.getColumnIndex("action_img"));
            String action_title = cursor.getString(cursor.getColumnIndex("action_title"));
            int action_timeState = cursor.getInt(cursor.getColumnIndex("action_timeState"));
            int action_count = cursor.getInt(cursor.getColumnIndex("action_count"));
            planInfo = new PlanInfo(plan_id, name, actionId, action_img, action_title, action_timeState, action_count);
        }
        cursor.close();
        db.close();
        return planInfo;
    }


    @SuppressLint("Range")
    public List<PlanInfo> queryPlanList(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<PlanInfo> list = new ArrayList<>();
        String sql = "select _id,username,action_id,action_img,action_title,action_timeState,action_count from plan_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int plan_id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            int actionId = cursor.getInt(cursor.getColumnIndex("action_id"));
            int action_img = cursor.getInt(cursor.getColumnIndex("action_img"));
            String action_title = cursor.getString(cursor.getColumnIndex("action_title"));
            int action_timeState = cursor.getInt(cursor.getColumnIndex("action_timeState"));
            int action_count = cursor.getInt(cursor.getColumnIndex("action_count"));
            list.add(new PlanInfo(plan_id, name, actionId, action_img, action_title, action_timeState, action_count));
        }
        cursor.close();
        db.close();
        return list;
    }

    //删除全部
    public int delete(String plan_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("plan_table", " _id=?", new String[]{plan_id});
        // 关闭数据库连接
        db.close();
        return delete;
    }

}
