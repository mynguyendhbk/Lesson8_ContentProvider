package edu.nguyenmy.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.nguyenmy.contentprovider.adapter.ItemAdapter;
import edu.nguyenmy.contentprovider.local.ItemColum;
import edu.nguyenmy.contentprovider.local.ItemProvider;
import edu.nguyenmy.contentprovider.model.Item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Item> mItems;
    private ItemAdapter mItemAdapter;
    private ListView mListView;
    private EditText mEdName, mEdPrice;
    private Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.lv_item);
        mEdName = (EditText) findViewById(R.id.ed_name);
        mEdPrice = (EditText) findViewById(R.id.ed_price);
        mBtnAdd = (Button)findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mItems = getData();

    }
    private ArrayList<Item> getData() {
        ArrayList<Item> items = new ArrayList<>();
        items.clear();
        Cursor cursor = getContentResolver().query(ItemProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                java.lang.String s = cursor.getString(cursor.getColumnIndex(ItemColum.ItemEntry.COLUM_NAME));
                java.lang.String s1 = cursor.getString(cursor.getColumnIndex(ItemColum.ItemEntry.COLUM_PRICE));
                items.add(new Item(s,s1));
            } while (cursor.moveToNext());
        }
        return items;
    }


    @Override
    public void onClick(View view) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemColum.ItemEntry.COLUM_NAME,mEdName.getText().toString());
        contentValues.put(ItemColum.ItemEntry.COLUM_PRICE,mEdPrice.getText().toString());
        Uri uri = getContentResolver().insert(
                ItemProvider.CONTENT_URI, contentValues);

        if (uri != null) {
            // reload data
            mItems.clear();
            mItems.addAll(getData());
            mItemAdapter = new ItemAdapter(this, mItems);
            mListView.setAdapter(mItemAdapter);
            mItemAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Insert failed", Toast.LENGTH_SHORT).show();
        }
    }
}
