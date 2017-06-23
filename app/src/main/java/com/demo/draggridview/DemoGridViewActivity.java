package com.demo.draggridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangxiao-ms on 2017/6/1.
 */

public class DemoGridViewActivity extends Activity {

    DragGridView gridView;
    List<String> items;
    BaseAdapter adapter = new MyListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_gridview);
        gridView = (DragGridView) findViewById(R.id.demo_grid_view);

        items = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            items.add("Item" + i);
        }
        gridView.setAdapter(adapter);
        gridView.setOnItemChanageListener(new DragGridView.OnItemChanageListener() {
            @Override
            public void onChange(int from, int to) {
                if (from < to) {
                    for (int i = from; i < to; i++) {
                        Collections.swap(items, i, i + 1);
                    }
                } else if (from > to) {
                    for (int i = from; i > to; i--) {
                        Collections.swap(items, i, i - 1);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.app_item, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.title.setText(items.get(position));
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void reorder(int oldPosition, int newPosition) {
            /*String obj = items.remove(oldPosition);
            items.add(newPosition, obj);
            notifyDataSetChanged();*/
        }

        class ViewHolder {
            public TextView title;
            public ImageView image;
        }
    }
}
