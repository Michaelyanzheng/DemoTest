package com.zheng.demotest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String CREATE_BOOK_TABLE = "create table Book_Table(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    private final String CREATE_CATEGORY = "create table Category_Table(" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";

    private Context mContext;

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_CATEGORY);

        Toast.makeText(mContext, "CREATE_BOOK_TABLE", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Book_Table");
        db.execSQL("drop table if exists Category_Table");

        onCreate(db);

        switch (oldVersion){

            case 1:

                db.execSQL(CREATE_CATEGORY);



                default:

        }
    }
}
