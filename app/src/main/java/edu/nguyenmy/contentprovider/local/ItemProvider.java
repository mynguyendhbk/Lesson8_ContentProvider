package edu.nguyenmy.contentprovider.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by DELL on 10/20/2017.
 */

public class ItemProvider extends ContentProvider {
    public static final String SCHEME = "content://";
    public static String TABLE_NAME = "vegetables";
    public static final String AUTHORITY = "edu.nguyenmy.contentprovider";
    public static final String URL_ITEM = SCHEME + AUTHORITY + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL_ITEM);
    public static UriMatcher uriMatcher;
    public DatabaseHelper mDatabaseHelper;
    public static final int URI_ITEM = 0;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, URI_ITEM);
    }

    public SQLiteDatabase mSqLiteDatabase;


    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_ITEM:
                return mSqLiteDatabase.query(TABLE_NAME,strings,s,strings1,s1,null,null);
            default:
                throw new IllegalArgumentException("Unkown Uri" + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        long rowID = mSqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case URI_ITEM:
                return mSqLiteDatabase.delete(TABLE_NAME,s, strings);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        switch (uriMatcher.match(uri)) {
            case URI_ITEM:
                return mSqLiteDatabase.update(TABLE_NAME, contentValues, s, strings);
            case UriMatcher.NO_MATCH:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
