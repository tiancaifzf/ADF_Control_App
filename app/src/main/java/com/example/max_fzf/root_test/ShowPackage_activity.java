package com.example.max_fzf.root_test;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by max-fzf on 26.03.16.
 */
public class ShowPackage_activity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ArrayList<HashMap<String, Object>> applist = new ArrayList<HashMap<String, Object>>();
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpinfo = new AppInfo();
            HashMap<String, Object> map = new HashMap<String, Object>();
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {//过滤掉系统程序
                Log.d("System software", "We met System " + i + "th software: " + packageInfo.packageName);
                continue;
            }
            map.put("appname", tmpinfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
            map.put("packagename", tmpinfo.packagename = packageInfo.packageName.toString());
            Drawable icon = null;
            icon = packageInfo.applicationInfo.loadIcon(getPackageManager()).getCurrent();
            //Bitmap a=drawableToBitmap( tmpinfo.appicon=packageInfo.applicationInfo.loadIcon(getPackageManager()));
            //Bitmap a=drawableToBitmap( R.android_logo);
            map.put("appicon", icon);
            applist.add(map);
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        SimpleAdapter listadapter = new SimpleAdapter(this, applist, R.layout.simple, new String[]{"appicon", "appname", "packagename"}, new int[]{R.id.ItemImage, R.id.ItemName, R.id.ItemInfo});
        listView.setAdapter(listadapter);
        listadapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Drawable) {
                    ImageView iv = (ImageView) view;
                    iv.setImageDrawable((Drawable) data);
                    return true;
                } else
                    return false;
            }
        });
    }
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
