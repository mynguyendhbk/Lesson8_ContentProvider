package edu.nguyenmy.contentprovider.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 10/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Vegetables";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "vegetables";
    private static final String CREATE_TABLE_ITEM =
            " CREATE TABLE " + TABLE_NAME
                    + " ( "
                    + ItemColum.ItemEntry._ID
                    + " INTEGER PRIMARY KEY, "
                    + ItemColum.ItemEntry.COLUM_NAME
                    +  " TEXT, "
                    + ItemColum.ItemEntry.COLUM_PRICE
                    + " TEXT " + ")";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tao bang du lieu
            sqLiteDatabase.execSQL(CREATE_TABLE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
