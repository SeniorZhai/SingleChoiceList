package com.zom.m;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int cur_pos = 0;// 当前显示的一行
	private String[] items_text = { "选项一", "选项二", "选项三", "选项四", "选项五" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Mydapter dapter = new Mydapter(this);
		ListView listview = (ListView) findViewById(R.id.listView);
		listview.setAdapter(dapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				cur_pos = position;// 更新当前行
				dapter.notifyDataSetChanged();
			}
		});
	}

	public class Mydapter extends BaseAdapter {
		private LayoutInflater inflater;

		public Mydapter(Context context) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return items_text.length;
		}

		@Override
		public Object getItem(int position) {
			return items_text[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.e("TEST", "refresh once");
			convertView = inflater.inflate(R.layout.item, null, false);
			TextView tv = (TextView) convertView.findViewById(R.id.tv);// 显示文字
			tv.setText(items_text[position]);
			if (position == cur_pos) {// 如果当前的行就是ListView中选中的一行，就更改显示样式
				convertView.setBackgroundColor(R.drawable.channel_list_item_bg_selected);// 更改整行的背景色
			}
			return convertView;
		}
	}
}
