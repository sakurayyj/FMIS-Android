package com.example.fmis.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.adapter.Data;
import com.example.fmis.adapter.MyPageAdapter;
import com.example.fmis.adapter.MyPagerAdapter1;
import com.example.fmis.fragment.GuanlianguizeFragment;
import com.example.fmis.fragment.JuleiFragment;
import com.example.fmis.fragment.MainFragment;
import com.example.fmis.fragment.MyFragment;
import com.example.fmis.fragment.YuceFragment;
import com.example.fmis.shouye.PretrearmentFragment;
import com.example.fmis.shouye.ShowDataFragment;
import com.example.fmis.zsgc.AddActivity;
import com.example.fmis.zsgc.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private BottomNavigationView navigationView;
    private ArrayList<Fragment> fragmentList;
    private ViewPager2 viewPager2;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    public static int id = 0; //????????????????????????activity?????????id
    private String ip = "192.168.43.63";                 //??????ip??????
    private String port = "6666";                //???????????????
    private Socket socketSend;          //????????????????????????ip???????????????????????????????????????????????????
    public static DataInputStream dis;        //??????
    public static DataOutputStream dos;       //?????????
    public  static boolean isRunning = false;          //????????????????????????
    public static List<Data> DataList = new ArrayList<>(); //?????????????????????
    private static final int TCP_SERVER_PORT = 999;//pycharm???port
    //??????
    private static int x1,x6,x13,year;
    private static Double x2,x3,x4,x5,x7,x8,x9,x10,x11,x12,y;
    private TextView huoqu,xieru,pycharm;
    private ImageView shouye,julei,guanlianguize,yuce;
    private Fragment f_shouye,f_julei,f_guanlianguize,f_yuce;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        f_shouye = new MainFragment(MainActivity.this);
        f_julei = new JuleiFragment(MainActivity.this);
        f_guanlianguize = new GuanlianguizeFragment(MainActivity.this);
        f_yuce = new YuceFragment(MainActivity.this);
        huoqu = findViewById(R.id.huoqu_mysql);
        xieru = findViewById(R.id.xieru_mysql);
        pycharm = findViewById(R.id.huoqu_pycharm);
        shouye = findViewById(R.id.i_shouye);
        julei = findViewById(R.id.i_julei);
        guanlianguize = findViewById(R.id.i_guanlianguize);
        yuce = findViewById(R.id.i_yuce);
        huoqu.setOnClickListener(this);
        xieru.setOnClickListener(this);
        shouye.setOnClickListener(this);
        julei.setOnClickListener(this);
        guanlianguize.setOnClickListener(this);
        yuce.setOnClickListener(this);
        id = getIntent().getIntExtra("id", 0);
        getId(); //??????activity???????????????ID
//        getSupportFragmentManager()    //
//                .beginTransaction()
//                .add(R.id.fra_containner,new MainFragment(MainActivity.this))   // ?????????R.id.fragment_container????????????fragment????????????
//                .commit();

        //initData();
        connectMySQL();  //???????????????
        init();
        pycharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click","??????pycharm");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("pycharm","????????????");
                        runTcpClient();
                    }}).start();
                //???Uri????????????
                //imageView.setImageResource(R.drawable.testimg);
            }
        });
    }


    private void init() {

    }


    private void runTcpClient() {
        try {
            Socket s = new Socket("192.168.43.63", TCP_SERVER_PORT);//??????host?????????????????????hostname???IP??????
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            Log.d("pycharm","????????????");
            //send output msg
            //String outMsg = "?????????TCP connecting to " + TCP_SERVER_PORT + System.getProperty("line.separator");
            String outMsg = "search" + System.getProperty("line.separator");
            out.write(outMsg);//????????????
            out.flush();
            Log.i("TcpClient", "sent: " + outMsg);
            //accept server response
            //String len = String.valueOf(in.read());//??????????????????????????????
            //Log.i("TcpClient", "received: " + len);
//            for(int j = 0;j < Integer.parseInt(len);j++){
//                System.out.println(in.readLine() + System.getProperty("line.separator"));
//            }
            //close connection
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData() {
        //????????????
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("??????","??????????????????");
                try {
                    dos.writeUTF("??????");
                    System.out.println("????????????");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getData() {
        //?????????????????????????????????????????????
        new Thread(new receive(), "????????????").start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.huoqu_mysql:
                DataList.clear();
                sendData();  //??????????????????
                //Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_LONG).show();
                break;
            case R.id.xieru_mysql:
                sendData2();
                //Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_LONG).show();
                break;
            case R.id.i_shouye:
                hideAllFragment( fragmentManager );
                addFragment( fragmentManager, f_shouye, "A" );
                showFragment( fragmentManager, f_shouye );
                break;
            case R.id.i_julei:
                hideAllFragment( fragmentManager );
                addFragment( fragmentManager, f_julei, "B" );
                showFragment( fragmentManager, f_julei );
                break;
            case R.id.i_guanlianguize:
                hideAllFragment( fragmentManager );
                addFragment( fragmentManager, f_guanlianguize, "C" );
                showFragment( fragmentManager, f_guanlianguize );
                break;
            case R.id.i_yuce:
                hideAllFragment( fragmentManager );
                addFragment( fragmentManager, f_yuce, "D" );
                showFragment( fragmentManager, f_yuce );
                break;

            default:
                break;
        }
    }
    private void showFragment(FragmentManager fm, Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.show( fragment );
        ft.commitAllowingStateLoss();
    }

    private void addFragment(FragmentManager fm, Fragment fragment, String tag) {
        if (!fragment.isAdded()&&null == fm.findFragmentByTag( tag )) {
            FragmentTransaction ft = fm.beginTransaction();
            fm.executePendingTransactions();
            ft.add( R.id.fra_containner, fragment, tag );
            ft.commitAllowingStateLoss();
        }

    }

    private void hideAllFragment(FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        if (!f_shouye.isHidden())
            ft.hide( f_shouye );

        if (!f_julei.isHidden())
            ft.hide( f_julei );

        if (!f_guanlianguize.isHidden()) {
            ft.hide( f_guanlianguize );
        }
        if (!f_yuce.isHidden()){
            ft.hide(f_yuce);
        }
        ft.commitAllowingStateLoss();
    }

    private void sendData2() {
        //????????????
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("??????","??????????????????");
                        try {
                            dos.writeUTF("??????");
                            dos.writeInt(DataList.size());
                            for(int i = 0;i < DataList.size();i++){
                                dos.writeInt(Integer.parseInt(String.valueOf(DataList.get(i).getX1())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX2())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX3())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX4())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX5())));
                                dos.writeInt(Integer.parseInt(String.valueOf(DataList.get(i).getX6())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX7())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX8())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX9())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX10())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX11())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getX12())));
                                dos.writeInt(Integer.parseInt(String.valueOf(DataList.get(i).getX13())));
                                dos.writeDouble(Double.parseDouble(String.valueOf(DataList.get(i).getY())));
                                dos.writeInt(Integer.parseInt(String.valueOf(DataList.get(i).getYear())));
                                System.out.println("????????????"+i);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
    }

    //????????????
    public class receive implements Runnable{
        @Override
        public void run() {
            while(isRunning){
                Log.d("????????????","????????????");
                try {
                    x1 = dis.readInt();
                    if(x1!=0){
                        System.out.println("??????x1,"+x1);
                    }
                    x2 = dis.readDouble();
                    x3 = dis.readDouble();
                    x4 = dis.readDouble();
                    x5 = dis.readDouble();
                    x6 = dis.readInt();
                    x7 = dis.readDouble();
                    x8 = dis.readDouble();
                    x9 = dis.readDouble();
                    x10 = dis.readDouble();
                    x11 = dis.readDouble();
                    x12 = dis.readDouble();
                    x13 = dis.readInt();
                    y = dis.readDouble();
                    year = dis.readInt();
                    Data d = new Data(x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,y,year);
                    DataList.add(d);
                    Log.d("????????????","????????????,??????????????????"+DataList.size());
//                    Message message = new Message();
//                    message.obj = d;
//                    handler.sendMessage(message);
                } catch (Exception e) {
                    System.out.println("????????????");
                    e.printStackTrace();
                }
            }
        }
    }

//    //???????????????????????????Handler???????????????????????????????????????Handler????????????????????????UI?????????
//    //Handler????????????????????????:(1)???????????????????????????????????????????????????????????????;(2)???????????????????????????????????????????????????????????????
//    private Handler handler = new Handler(Looper.myLooper()){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            System.out.println("????????????");
//            showNewMessage();
//        }
//    };

    private void connectMySQL() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("??????");
                    socketSend = new Socket(ip, Integer.parseInt(port));
                    isRunning = true;
                    dis = new DataInputStream(socketSend.getInputStream());
                    dos = new DataOutputStream(socketSend.getOutputStream());
                    System.out.println("??????dos");
                    System.out.println("????????????");
                    getData();   //?????????????????????
                } catch (Exception e) {
                    Log.e("TAG",e.toString());
                    e.printStackTrace();
                    //?????????????????????????????????
                    Looper.prepare();
                    //Toast????????????????????????????????????
                    Toast.makeText(MainActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                    //?????????????????????
                    Looper.loop();
                }
            }
        }).start();
    }

    private void getId() {
//        if (id == 1) { //AddActivity????????????
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_main, new MainFragment(MainActivity.this, navigationView, fragmentManager))
//                    .addToBackStack(null)
//                    .commit();
//        }
        if (id == 1) { //AddActivity????????????
            getSupportFragmentManager()
                    .beginTransaction()
                    //.replace(R.id.fra_containner, new MainFragment(MainActivity.this, navigationView, fragmentManager))
                    .replace(R.id.fra_containner, new MainFragment(MainActivity.this))
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void initData() {
        //navigationView = findViewById(R.id.nav_view);
        //fragmentManager = getSupportFragmentManager();
        fragmentManager = getSupportFragmentManager();
        //?????????fragment????????????????????????
        fragmentList = new ArrayList<>();
        //fragmentList.add(new ShowDataFragment(MainActivity.this));
        fragmentList.add(new ShowDataFragment(MainActivity.this));
        //fragmentList.add(new MainFragment(MainActivity.this,navigationView,fragmentManager));
        fragmentList.add(new JuleiFragment(MainActivity.this));
        fragmentList.add(new GuanlianguizeFragment(MainActivity.this));
        fragmentList.add(new YuceFragment(MainActivity.this));
        fragmentList.add(new MyFragment());
//        //?????????viewpager
//        viewPager2 = findViewById(R.id.fragment_main);
//        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//                                 @Override
//                                 public int getCount() {
//                                     return fragmentList.size();
//                                 }
//
//                                 @NonNull
//                                 @NotNull
//                                 @Override
//                                 public Fragment getItem(int position) {
//                                     return fragmentList.get(position);
//                                 }
//                             }
//        );
          viewPager2.setAdapter(new MyPageAdapter(this,fragmentList));
//        viewPager = findViewById(R.id.fragment_main);
//        viewPager.setAdapter(new MyPagerAdapter1(fragmentList));
        //?????????????????????
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.navigation_home);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                navigationView.setSelectedItemId(navigationView.getMenu().getItem(position).getItemId());
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.navigation_home:
                viewPager2.setCurrentItem(0);
                //Fragment fragment = new ShowDataFragment(MainActivity.this);
                return true;
            case R.id.navigation_julei:  //??????
                viewPager2.setCurrentItem(1);
                return true;
            case R.id.navigation_guanlianguize:  //????????????
                viewPager2.setCurrentItem(2);
                return true;
            case R.id.navigation_yuce:  //???????????????
                viewPager2.setCurrentItem(3);
                return true;
            case R.id.navigation_wode:
                viewPager2.setCurrentItem(4);
                return true;
        }
        return true;

    }



}