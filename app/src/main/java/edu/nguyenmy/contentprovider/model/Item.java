package edu.nguyenmy.contentprovider.model;

import android.content.ContentValues;
import android.database.Cursor;

import edu.nguyenmy.contentprovider.local.ItemColum;

/**
 * Created by DELL on 10/20/2017.
 */

public class Item {
    private int mID;
    private java.lang.String mName;
    private java.lang.String mPrice;

    public Item(java.lang.String mName, java.lang.String mPrice) {
        this.mName = mName;
        this.mPrice = mPrice;
    }

    public Item(Cursor cursor) {
        mID = cursor.getInt(cursor.
                getColumnIndex(ItemColum.ItemEntry._ID));
        mName = cursor.getString(cursor.
                getColumnIndex(ItemColum.ItemEntry.COLUM_NAME));
        mPrice = cursor.getString(cursor.
                getColumnIndex(ItemColum.ItemEntry.COLUM_PRICE));
    }

    public int getmID() {
        return mID;
    }

    public java.lang.String getmName() {
        return mName;
    }

    public java.lang.String getmPrice() {
        return mPrice;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public void setmName(java.lang.String mName) {
        this.mName = mName;
    }

    public void setmPrice(java.lang.String mPrice) {
        this.mPrice = mPrice;
    }

}
