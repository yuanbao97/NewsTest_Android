package com.example.newstest.avtivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newstest.R;
import com.example.newstest.fragment.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        String newsTitle = getIntent().getStringExtra("newsTitle");
        String newsContent = getIntent().getStringExtra("newsContent");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent);
    }
}