package com.example.max_fzf.root_test;

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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;

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

	public lvButtonAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource, String[] from, int[] to,Context context) {
		mAppList = appList;
		mContext = c;
		hcontext=context;
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//return null;

		if (convertView != null) {
			itemView = (ItemView) convertView.getTag();
		} else {
			convertView = mInflater.inflate(R.layout.simple, null);
			itemView = new ItemView();
			itemView.ItemImage = (ImageView)convertView.findViewById(valueViewID[0]);
			itemView.ItemName = (TextView)convertView.findViewById(valueViewID[1]);
			itemView.ItemInfo = (TextView)convertView.findViewById(valueViewID[2]);
			itemView.ItemButton = (Switch)convertView.findViewById(valueViewID[3]);
			convertView.setTag(itemView);
		}

		HashMap<String, Object> appInfo = mAppList.get(position);
		if (appInfo != null) {


			final String name = (String) appInfo.get(keyString[1]);
			final String info = (String) appInfo.get(keyString[2]);
			itemView.ItemName.setText(name);
			itemView.ItemInfo.setText(info);
			//itemView.ItemImage.setImageDrawable(itemView.ItemImage.getResources().getDrawable(mid));
			itemView.ItemImage.setImageDrawable((Drawable) appInfo.get(keyString[0]));
			//itemView.ItemButton.setBackgroundDrawable(itemView.ItemButton.getResources().getDrawable(bid));
			//itemView.ItemButton.setOnClickListene
			// r(new ItemButton_Click(position));
			Context s=ShowPackage_activity.getContext();
			Switch sh= (Switch) itemView.ItemButton;
			sh.setChecked(ShowPackage_activity.boolean_statue(hcontext,info));
			sh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
			{
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					if (isChecked)
					{
						Log.d("!!!!!", info);
						//ShowPackage_activity s = new ShowPackage_activity();
						//s.edit_true(name);
					}
					else
					{
						//ShowPackage_activity s = new ShowPackage_activity();
						//s.edit_false(name);
					}
				}
			});
		}
		return convertView;
	}

	class ItemButton_Click implements OnClickListener {
		private int position;

		ItemButton_Click(int pos) {
			position = pos;
		}
        public void change(){

		}
		@Override
		public void onClick(View v) {
			int vid=v.getId();
			if (vid == itemView.ItemButton.getId())
				Log.v("ola_log",String.valueOf(position) );
		}
	}
}