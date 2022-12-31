package com.example.fmis.shouye;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fmis.R;
import com.example.fmis.information.X10Activity;
import com.example.fmis.information.X11Activity;
import com.example.fmis.information.X12Activity;
import com.example.fmis.information.X13Activity;
import com.example.fmis.information.X1Activity;
import com.example.fmis.information.X2Activity;
import com.example.fmis.information.X3Activity;
import com.example.fmis.information.X4Activity;
import com.example.fmis.information.X5Activity;
import com.example.fmis.information.X6Activity;
import com.example.fmis.information.X7Activity;
import com.example.fmis.information.X8Activity;
import com.example.fmis.information.X9Activity;
import com.example.fmis.information.YActivity;
import com.example.fmis.zsgc.AddActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class ShowDataFragment extends Fragment implements View.OnClickListener {
    private Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    private Activity activity;
    private SwipeRefreshLayout swipeRefresh;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageView m_x1,m_x2,m_x3,m_x4,m_x5,m_x6,m_x7,m_x8,m_x9,m_x10,m_x11,m_x12,m_x13,m_y;
    private TextView t_x1,t_x2,t_x3,t_x4,t_x5,t_x6,t_x7,t_x8,t_x9,t_x10,t_x11,t_x12,t_x13,t_y;

    public ShowDataFragment( Activity activity) {
        this.activity = activity;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_show_data, container, false);
        //TextView txt = inflate.findViewById(R.id.txt1);
        //txt.setText(string);
//        swipeRefresh = (SwipeRefreshLayout)inflate.findViewById(R.id.swip_refresh);
//        swipeRefresh.setColorSchemeColors(R.color.black);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refresh(inflate);
//            }
//        });
        initAllData(inflate);

        //轮播图
        initData();
        initView(inflate);
        return inflate;
    }

    private void initAllData(View inflate) {
        m_x1 = inflate.findViewById(R.id.i_congye);
        t_x1 = inflate.findViewById(R.id.t_congye);
        m_x2 = inflate.findViewById(R.id.i_gongzi);
        t_x2 = inflate.findViewById(R.id.t_gongzi);
        m_x3 = inflate.findViewById(R.id.i_lingshou);
        t_x3 = inflate.findViewById(R.id.t_lingshou);
        m_x4 = inflate.findViewById(R.id.i_kezhipei);
        t_x4 = inflate.findViewById(R.id.t_kezhipei);
        m_x5 = inflate.findViewById(R.id.i_zhichu);
        t_x5 = inflate.findViewById(R.id.t_zhichu);
        m_x6 = inflate.findViewById(R.id.i_renkou);
        t_x6 = inflate.findViewById(R.id.t_renkou);
        m_x7 = inflate.findViewById(R.id.i_touzi);
        t_x7 = inflate.findViewById(R.id.t_touzi);
        m_x8 = inflate.findViewById(R.id.i_shengchanzongzhi);
        t_x8 = inflate.findViewById(R.id.t_shengchanzongzhi);
        m_x9 = inflate.findViewById(R.id.i_diyichanye);
        t_x9 = inflate.findViewById(R.id.t_diyichanye);
        m_x10 = inflate.findViewById(R.id.i_shuishou);
        t_x10 = inflate.findViewById(R.id.t_shuishou);
        m_x11 = inflate.findViewById(R.id.i_jiagezhishu);
        t_x11 = inflate.findViewById(R.id.t_jiagezhishu);
        m_x12 = inflate.findViewById(R.id.i_chanzhibi);
        t_x12 = inflate.findViewById(R.id.t_chanzhibi);
        m_x13 = inflate.findViewById(R.id.i_xiaofeishuiping);
        t_x13 = inflate.findViewById(R.id.t_xiaofeishuiping);
        m_y = inflate.findViewById(R.id.i_caizhengshouru);
        t_y = inflate.findViewById(R.id.t_caizhengshouru);
        m_x1.setOnClickListener(this);
        t_x1.setOnClickListener(this);
        m_x2.setOnClickListener(this);
        t_x2.setOnClickListener(this);
        m_x3.setOnClickListener(this);
        t_x3.setOnClickListener(this);
        m_x4.setOnClickListener(this);
        t_x4.setOnClickListener(this);
        m_x5.setOnClickListener(this);
        t_x5.setOnClickListener(this);
        m_x6.setOnClickListener(this);
        t_x6.setOnClickListener(this);
        m_x7.setOnClickListener(this);
        t_x7.setOnClickListener(this);
        m_x8.setOnClickListener(this);
        t_x8.setOnClickListener(this);
        m_x9.setOnClickListener(this);
        t_x9.setOnClickListener(this);
        m_x10.setOnClickListener(this);
        t_x10.setOnClickListener(this);
        m_x11.setOnClickListener(this);
        t_x11.setOnClickListener(this);
        m_x12.setOnClickListener(this);
        t_x12.setOnClickListener(this);
        m_x13.setOnClickListener(this);
        t_x13.setOnClickListener(this);
        m_y.setOnClickListener(this);
        t_y.setOnClickListener(this);
    }

    private void refresh(View inflate) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.view_show_data, ShowDataFragment.this);
                        fragmentTransaction.commit();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
    private void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.caizheng2);
        imagePath.add(R.drawable.caizheng2);
        imagePath.add(R.drawable.caizheng2);
        imageTitle.add("我是海鸟一号");
        imageTitle.add("2022年财政如何发力...");
        imageTitle.add("我是海鸟三号");
    }

    private void initView(View inflate) {
        mMyImageLoader = new MyImageLoader();
        mBanner = inflate.findViewById(R.id.mBanner);
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.Default);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(2000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(activity, "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
                    }
                })
                //开始调用的方法，启动轮播图。
                .start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.i_congye:
            case R.id.t_congye:
                Intent intent = new Intent(activity, X1Activity.class);
                startActivity(intent);
                break;
            case R.id.i_gongzi:
            case R.id.t_gongzi:
                Intent intent2 = new Intent(activity, X2Activity.class);
                startActivity(intent2);
                break;
            case R.id.i_lingshou:
            case R.id.t_lingshou:
                Intent intent3 = new Intent(activity, X3Activity.class);
                startActivity(intent3);
                break;
            case R.id.i_kezhipei:
            case R.id.t_kezhipei:
                Intent intent4 = new Intent(activity, X4Activity.class);
                startActivity(intent4);
                break;
            case R.id.i_zhichu:
            case R.id.t_zhichu:
                Intent intent5 = new Intent(activity, X5Activity.class);
                startActivity(intent5);
                break;
            case R.id.i_renkou:
            case R.id.t_renkou:
                Intent intent6 = new Intent(activity, X6Activity.class);
                startActivity(intent6);
                break;
            case R.id.i_touzi:
            case R.id.t_touzi:
                Intent intent7 = new Intent(activity, X7Activity.class);
                startActivity(intent7);
                break;
            case R.id.i_shengchanzongzhi:
            case R.id.t_shengchanzongzhi:
                Intent intent8 = new Intent(activity, X8Activity.class);
                startActivity(intent8);
                break;
            case R.id.i_diyichanye:
            case R.id.t_diyichanye:
                Intent intent9 = new Intent(activity, X9Activity.class);
                startActivity(intent9);
                break;
            case R.id.i_shuishou:
            case R.id.t_shuishou:
                Intent intent10 = new Intent(activity, X10Activity.class);
                startActivity(intent10);
                break;
            case R.id.i_jiagezhishu:
            case R.id.t_jiagezhishu:
                Intent intent11 = new Intent(activity, X11Activity.class);
                startActivity(intent11);
                break;
            case R.id.i_chanzhibi:
            case R.id.t_chanzhibi:
                Intent intent12 = new Intent(activity, X12Activity.class);
                startActivity(intent12);
                break;
            case R.id.i_xiaofeishuiping:
            case R.id.t_xiaofeishuiping:
                Intent intent13 = new Intent(activity, X13Activity.class);
                startActivity(intent13);
                break;
            case R.id.i_caizhengshouru:
            case R.id.t_caizhengshouru:
                Intent intenty = new Intent(activity, YActivity.class);
                startActivity(intenty);
                break;
        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }
}