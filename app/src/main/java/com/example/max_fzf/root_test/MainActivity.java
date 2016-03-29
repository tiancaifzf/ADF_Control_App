package com.example.max_fzf.root_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    //public boolean ischecked;
    Process localProcess = null;
    OutputStream localOutputStream = null;
    public static final String KEY="com.example.max_fzf.root_test";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences spref = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = spref.edit();
        Switch ss=(Switch)findViewById(R.id.switch1);
        boolean boolvalue=spref.getBoolean("KEY_Boolean",false);
        ss.setChecked(boolvalue);
        Button rb= (Button) findViewById(R.id.button);
        Button jump= (Button) findViewById(R.id.button2);

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            jump();
            }
        });
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    localProcess = Runtime.getRuntime().exec("su");
                    localOutputStream = localProcess.getOutputStream();
                    DataOutputStream localDataOutputStream = new DataOutputStream(localOutputStream);
                    localDataOutputStream.writeBytes("reboot\n");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                localOutputStream = localProcess.getOutputStream();

            }
        });
        ss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        localProcess = Runtime.getRuntime().exec("su");
                        localOutputStream = localProcess.getOutputStream();
                        DataOutputStream localDataOutputStream = new DataOutputStream(localOutputStream);
                        String path = "\"Lcom/antutu/ABenchMark/ABenchMarkStart;,VL,onCreate,Lcom/example/max_fzf/hook/MainActivity;,VL,onCreate\"";
                        localDataOutputStream.writeBytes("mount -o remount,rw /system\n");
                        localDataOutputStream.writeBytes("rm -r /system/df_file\n");
                        localDataOutputStream.writeBytes("echo \"Lcom/unity3d/ads/android/UnityAds;,Z,canShow,Lcom/example/max_fzf/hook/MainActivity;,Z,canShow\" > /system/df_file\n");
                       // localDataOutputStream.writeBytes("echo \"Lcom/google/android/gms/ads/AdView;,VL,loadAd,Lcom/example/max_fzf/hook/MainActivity;,VL,deleteAd\" >> /system/df_file\n");
                        localDataOutputStream.writeBytes("chmod 644 /system/df_file\n");
                        localDataOutputStream.writeBytes("mount -o remount,ro /system\n");
                        //isChecked=true;
                        editor.putBoolean("KEY_Boolean",true);
                        editor.apply();
                        editor.commit();
                        Log.d("ADF!!","turn on !success!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        localProcess = Runtime.getRuntime().exec("su");
                        localOutputStream = localProcess.getOutputStream();
                        DataOutputStream localDataOutputStream = new DataOutputStream(localOutputStream);
                        String path = "\"Lcom/antutu/ABenchMark/ABenchMarkStart;,VL,onCreate,Lcom/example/max_fzf/hook/MainActivity;,VL,onCreate\"";
                        localDataOutputStream.writeBytes("mount -o remount,rw /system\n");
                        localDataOutputStream.writeBytes("rm -r /system/df_file\n");
                        //isChecked=false;
                        editor.putBoolean("KEY_Boolean",false);
                        editor.commit();
                        Log.d("ADF!!", "turn off !success!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }//非选中时 do some thing
                }
            }
        });

    }
    public void jump(){
        Intent J=new Intent(this,ShowPackage_activity.class);
        startActivity(J);
    }
}
