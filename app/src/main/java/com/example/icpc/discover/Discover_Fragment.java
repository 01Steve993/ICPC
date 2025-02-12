package com.example.icpc.discover;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icpc.R;
import com.example.icpc.database.DatabaseHelper;

import java.util.List;

public class Discover_Fragment extends Fragment {
    private ViewPager2 viewPager;
    private List<Integer> imageList;
    private Handler handler = new Handler();
    private int currentPage = 0;
    private final long DELAY_MS = 3000; // 轮播间隔时间
    private Runnable runnable = new Runnable() {
        public void run() {
            if (currentPage == imageList.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(this, DELAY_MS);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        viewPager = view.findViewById(R.id.slideshow);

        // 添加轮播图图片资源
        imageList = List.of(R.drawable.image1, R.drawable.image2, R.drawable.image3);

        // 创建适配器
        Discover_image_Adapter discoverImageAdapter = new Discover_image_Adapter(imageList);
        viewPager.setAdapter(discoverImageAdapter);

        // 启动轮播
        handler.postDelayed(runnable, DELAY_MS);

        // 初始化数据库助手
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());

        // 从数据库中获取随机文章列表
        List<discover_article> articleList = dbHelper.getRandomArticles(7);

        // 设置RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DiscoverArticleAdapter articleAdapter = new DiscoverArticleAdapter(getContext(), articleList);
        recyclerView.setAdapter(articleAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 停止轮播
        handler.removeCallbacks(runnable);
    }
}