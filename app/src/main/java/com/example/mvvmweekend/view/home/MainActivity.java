package com.example.mvvmweekend.view.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmweekend.R;
import com.example.mvvmweekend.data.models.Article;
import com.example.mvvmweekend.data.models.ArticleListResponse;
import com.example.mvvmweekend.view.articleDetail.ArticleDetailActivity;
import com.example.mvvmweekend.view.home.adapter.ArticleAdapter;

import java.util.ArrayList;

import okhttp3.Interceptor;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.init();
        initUI();

        fetchDataFromAPI();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ArticleAdapter(this, new ArrayList<>(), article -> {
            gotoDetailScreen(article);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void gotoDetailScreen(Article article) {
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }

    private void fetchDataFromAPI() {
        homeViewModel.findAllArticles(1,15).observe(this, new Observer<ArticleListResponse>() {
            @Override
            public void onChanged(ArticleListResponse articleListResponse) {
                adapter.setData(articleListResponse.getArticleList());
            }
        });
    }
}