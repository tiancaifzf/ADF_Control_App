package com.example.max_fzf.root_test;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by max-fzf on 26.03.16.
 */
public class ShowPackage_activity extends Activity {
    private static Context mContext ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
         SharedPreferences spref = getPreferences(MODE_PRIVATE);
         SharedPreferences.Editor editor = spref.edit();
         mContext = this.getApplicationContext();
        if(mContext == null) {
            Log.d("%%%%%%%%%%%%%%%%%%%%", "NOTHING!!");
        }
        ArrayList<HashMap<String, Object>> applist = new ArrayList<HashMap<String, Object>>();
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpinfo = new AppInfo();
            HashMap<String, Object> map = new HashMap<String, Object>();
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {//过滤掉系统程序
               // Log.d("System software", "We met System " + i + "th software: " + packageInfo.packageName);
                continue;
            }
            map.put("appicon", packageInfo.applicationInfo.loadIcon(getPackageManager()).getCurrent());
            map.put("appname", tmpinfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
            map.put("packagename", tmpinfo.packagename = packageInfo.packageName.toString());
            map.put("ItemButton",R.drawable.android_logo);
            //boolean boolvalue=spref.getBoolean(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString(),false);
           // map.put("check_boolean",boolvalue);
            //editor.putBoolean(packageInfo.packageName.toString(), false);
            //editor.apply();
            //editor.commit();
            //Drawable icon = null;
            //Bitmap a=drawableToBitmap( tmpinfo.appicon=packageInfo.applicationInfo.loadIcon(getPackageManager()));
            //Bitmap a=drawableToBitmap( R.android_logo);
            applist.add(map);
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        // SimpleAdapter listadapter = new SimpleAdapter(this, applist, R.layout.simple, new String[]{"appicon", "appname", "packagename"}, new int[]{R.id.ItemImage, R.id.ItemName, R.id.ItemInfo});
        //listView.setAdapter(listadapter);



        final lvButtonAdapter listItemAdapter = new lvButtonAdapter(
                this,
                applist,//数据源
                R.layout.simple,//ListItem的XML实现
                new String[]{"appicon", "appname", "packagename","ItemButton"},
                new int[]{R.id.ItemImage, R.id.ItemName, R.id.ItemInfo,R.id.ItemButton},
                mContext
        );
        listView.setAdapter(listItemAdapter);

    }

    public static void edit_true(Context ctx,String app_name){
        SharedPreferences spref = ctx.getSharedPreferences("ShowPackage_activity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.putBoolean(app_name,true);
        editor.apply();
        editor.commit();
    }
    public static void edit_false(Context ctx,String app_name){
        SharedPreferences spref = ctx.getSharedPreferences("ShowPackage_activity", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = spref.edit();
        editor.putBoolean(app_name,false);
        editor.apply();
        editor.commit();
    }
    public static boolean boolean_statue(Context ctx,String name){
        SharedPreferences spref = ctx.getSharedPreferences("ShowPackage_activity", Context.MODE_PRIVATE);
        boolean boolvalue=spref.getBoolean(name, false);
        Log.d("$$$$$$$$$$$$$$$$$$$","Name:"+name);
        Log.d("$$$$$$$$$$$$$$$$$$$","Bool:"+boolvalue);
        return boolvalue;
    }
    public static Context getContext(){
        return mContext;
    }
}
