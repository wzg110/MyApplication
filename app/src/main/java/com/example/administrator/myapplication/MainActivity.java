package com.example.administrator.myapplication;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private  List<PackageInfo> arr=new ArrayList<>();
    private boolean yingyongbao=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) this.findViewById(R.id.btn);
        arr=getAllApps(getApplicationContext());
        for (int i=0;i<arr.size();i++) {
            if ("com.tencent.android.qqdownloader".equals(arr.get(i).packageName)){
//                goToMarket(getApplicationContext(),"com.yunding.dut");

                yingyongbao=true;
                return;
            }
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (yingyongbao){
//                    goToMarket(getApplicationContext(),"com.yunding.dut");
//                }else{
//                    Toast.makeText(getApplicationContext(),"没有安装应用宝",Toast.LENGTH_SHORT).show();
//                }
//                BigDecimal a =new BigDecimal("5");
//                BigDecimal b=new BigDecimal("6");
//                System.out.println(b.subtract(a));
//                btn.setText(b.subtract(a)+"");
                btn.setText(new SimpleDateFormat("HH:mm:ss").format(TimeZone.getDefault().getOffset(5*60*1000)));

            }
        });

    }


    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            goToMarket.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<PackageInfo> getAllApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        //获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            //判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }


}
