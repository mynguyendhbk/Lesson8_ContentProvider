package edu.nguyenmy.contentprovider.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.nguyenmy.contentprovider.R;
import edu.nguyenmy.contentprovider.model.Item;


/**
 * Created by DELL on 10/23/2017.
 */

public class ItemAdapter extends BaseAdapter {
    private ArrayList<Item> mListItem;
    private LayoutInflater mLayoutInflater;
    private TextView mTvName, mTvPrice;

    public ItemAdapter(Context context, ArrayList<Item> mListItem) {
        this.mListItem = mListItem;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListItem.size();
    }

    @Override
    public Object getItem(int i) {
        return mListItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Item item = mListItem.get(i);

        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_view,viewGroup,false);
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mTvPrice = (TextView) view.findViewById(R.id.tv_price);
            mTvName.setText(item.getmName());
            mTvPrice.setText(item.getmPrice());
        }
        return view;
    }
}
