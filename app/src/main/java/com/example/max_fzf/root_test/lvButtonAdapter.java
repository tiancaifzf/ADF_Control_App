package com.example.max_fzf.root_test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.Toast;

public class lvButtonAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, Object>> mAppList;
	private LayoutInflater mInflater;
	private Context mContext;
	private String[] keyString;
	private int[] valueViewID;
	private ItemView itemView;
	public  Context hcontext;

	//SharedPreferences spref = getPreferences(MODE_PRIVATE);
	private class ItemView {
		ImageView ItemImage;
		TextView ItemName;
		TextView ItemInfo;
		Button ItemButton;
		Boolean check_boolean;
	}

	public lvButtonAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,Context context ) {
		mAppList = appList;
		mContext = c;
		hcontext=context;
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return 0;
		return mAppList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//return null;
		return mAppList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		//return 0;
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return null;
		if (convertView != null) {
			itemView = (ItemView) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.simple, null);
			itemView = new ItemView();
			itemView.ItemImage = (ImageView)convertView.findViewById(R.id.ItemImage);
			itemView.ItemName = (TextView)convertView.findViewById(R.id.ItemName);
			itemView.ItemInfo = (TextView)convertView.findViewById(R.id.ItemInfo);
			itemView.ItemButton = (CheckBox)convertView.findViewById(R.id.ItemButton);
			convertView.setTag(itemView);
		}
		final HashMap<String, Object> appInfo = mAppList.get(position);
		if (appInfo != null) {
			final String name = (String) appInfo.get("appname");
			final String info = (String) appInfo.get("packagename");
			itemView.ItemName.setText(name);
			itemView.ItemInfo.setText(info);
			itemView.ItemImage.setImageDrawable((Drawable) appInfo.get("appicon"));

		}
		CheckBox c= (CheckBox) itemView.ItemButton;
		String a=itemView.ItemInfo.getText().toString();
		c.setChecked(ShowPackage_activity.boolean_statue(hcontext, a));
		if (c.isChecked())
		{
			c.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View v) {
					ShowPackage_activity.edit_false(hcontext, (String) mAppList.get(position).get("packagename"));
				}
			});
		}
		else
		{
			c.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					ShowPackage_activity.edit_true(hcontext, (String) mAppList.get(position).get("packagename"));
					Log.d("!!!!!!", "INFO:" + (String) mAppList.get(position).get("packagename"));
					try {
						ShowPackage_activity.Hook_function((String) mAppList.get(position).get("packagename"));
						Log.d("1111111111111","11111111111111");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}

		return convertView;
	}
}