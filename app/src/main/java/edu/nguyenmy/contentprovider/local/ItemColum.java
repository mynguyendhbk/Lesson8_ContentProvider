package edu.nguyenmy.contentprovider.local;

import android.provider.BaseColumns;

/**
 * Created by DELL on 10/20/2017.
 */

public class ItemColum {
    public ItemColum(){

    }
    public class ItemEntry implements BaseColumns{
        public static final String COLUM_NAME = "name";
        public static final String COLUM_PRICE = "price";
    }
}
