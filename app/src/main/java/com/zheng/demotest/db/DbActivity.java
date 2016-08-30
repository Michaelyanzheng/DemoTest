package com.zheng.demotest.db;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class DbActivity extends Activity implements View.OnClickListener {

    private Button mBtnCreateDb;
    private Button mBtnAddData;

    private MySQLiteOpenHelper mMySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        mMySQLiteOpenHelper = new MySQLiteOpenHelper(this,"BookStore.db",null,2);

        mBtnCreateDb = (Button) findViewById(R.id.btn_create_db);
        mBtnAddData = (Button) findViewById(R.id.btn_add_data);

        mBtnCreateDb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_create_db:

                mMySQLiteOpenHelper.getWritableDatabase();

                break;

            case R.id.btn_add_data:

                SQLiteDatabase sqLiteDatabase = mMySQLiteOpenHelper.getWritableDatabase();


                /**
                 * "author text," +
                 "price real," +
                 "pages integer," +
                 "name text)";
                 */
                ContentValues contentValues = new ContentValues();

                contentValues.put("name","zheng");
                contentValues.put("author","michael");
                contentValues.put("pages",123);
                contentValues.put("price",99.9);

                sqLiteDatabase.insert("Book_Table",null,contentValues);

                contentValues.clear();

                contentValues.put("price",10.9);

                sqLiteDatabase.update("Book_Table",contentValues,"name = ?",new String []{"zheng"});

                contentValues.clear();

                sqLiteDatabase.delete("Book_Table","pages > ?",new String []{"500"});


                Cursor cursor = sqLiteDatabase.query("Book_Table",null,null,null,null,null,null);

                while (cursor.moveToNext()){

                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int price = cursor.getInt(cursor.getColumnIndex("price"));
                }

                cursor.close();

                sqLiteDatabase.execSQL("insert into Book_Table {name,author,pages,price} values{?,?,?,?}",
                        new String []{"zheng","michael","12","10"});

                sqLiteDatabase.execSQL("update Book_Table set price = ? where name = ?",
                        new String [] {"12","zheng"});

                sqLiteDatabase.execSQL("update Book_Table set price = ? where name = ?",
                        new String[] {"16","michael"}
                );

                sqLiteDatabase.execSQL("delete from Book_Table where pages > ?",new String []{"500"});

                sqLiteDatabase.beginTransaction();

                sqLiteDatabase.execSQL("select * from Book_Table",null);

                sqLiteDatabase.setTransactionSuccessful();

                sqLiteDatabase.endTransaction();




                break;
        }
    }
}
