package com.example.fmis.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.adapter.MyPageAdapter;
import com.example.fmis.shouye.PretrearmentFragment;
import com.example.fmis.shouye.ShowDataFragment;
import com.example.fmis.zsgc.AddActivity;
import com.example.fmis.zsgc.ChangeActivity;
import com.example.fmis.zsgc.DeleteActivity;
import com.example.fmis.zsgc.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainFragment extends Fragment implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MyPageAdapter myPageAdapter;
    private ArrayList<Fragment> fragmentList;
    private String[] strs = {"数据展示","数据预处理"};
    private Activity activity;
    private CircleImageView cv;
    private FragmentManager fragmentManager;
    private BottomNavigationView navigationView;
    private TabLayout tabblayout;
    private Button zsgc;  //增删改查
    private SearchView searchView;
    private int id; //判断要跳到哪个页面去



//
//    public MainFragment(Activity activity,BottomNavigationView navigationView,FragmentManager fragmentManager) {
//        this.activity = activity;
//        this.navigationView = navigationView;
//        this.fragmentManager = fragmentManager;
//    }
    public MainFragment(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);
        cv = inflate.findViewById(R.id.touxiang);
        tabblayout = inflate.findViewById(R.id.tab_layout);
        searchView = inflate.findViewById(R.id.search_view);
        searchView.setOnClickListener(this);
        cv.setOnClickListener(this);
        init(inflate);
        return inflate;
    }



    private void init(View inflate) {
        viewPager2 = inflate.findViewById(R.id.view_pager);
        tabLayout = inflate.findViewById(R.id.tab_layout);
        fragmentList = new ArrayList<>();
        fragmentList.add(new ShowDataFragment(activity));
        fragmentList.add(new PretrearmentFragment(activity));
        myPageAdapter = new MyPageAdapter((FragmentActivity) activity,fragmentList);
        viewPager2.setAdapter(myPageAdapter);
        Log.d("id",String.valueOf(id));
        viewPager2.setCurrentItem(0);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                tab.setText(strs[position]);
            }
        }).attach();
        //setIndicator(tabblayout,20,20);
        //初始化增删改查
        zsgc = inflate.findViewById(R.id.zsgc);
        zsgc.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.touxiang:
                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(view.getId(),new Fragment_my());
                //transaction.commit();
                navigationView.setSelectedItemId(R.id.navigation_wode);
                break;
            case R.id.zsgc:
                showPopupMenu(view);
                break;
            case R.id.search_view:
                Intent intent = new Intent(activity, SearchActivity.class);
                startActivity(intent);
        }
    }
    @SuppressLint("RestrictedApi")
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(activity, view, Gravity.BOTTOM);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.add_more, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().equals("添加数据")){
                    Intent intent = new Intent(activity, AddActivity.class);
                    startActivity(intent);
                }else if(item.getTitle().equals("删除数据")){
                    Intent intent = new Intent(activity, DeleteActivity.class);
                    startActivity(intent);
                }else if(item.getTitle().equals("修改数据")){
                    Intent intent = new Intent(activity, ChangeActivity.class);
                    startActivity(intent);
                }
                //Toast.makeText(activity.getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });
        //显示菜单图标
        setForceShowIcon(popupMenu);
        popupMenu.show();
    }

    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    //设置Tablayout
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
