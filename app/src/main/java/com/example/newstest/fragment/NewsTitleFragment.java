package com.example.newstest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstest.R;
import com.example.newstest.adapter.NewsAdapter;
import com.example.newstest.response.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        RecyclerView newsTitleRecycleView = (RecyclerView) view.findViewById(R.id.news_title_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsAdapter = new NewsAdapter(getNews(), getActivity());
        newsTitleRecycleView.setLayoutManager(layoutManager);
        newsTitleRecycleView.setAdapter(newsAdapter);
        return view;
    }

    private List getNews() {
        List<News> newsList = new ArrayList<>();
        for(int i= 1; i <= 50; i++) {
            News news = new News();
            news.setId(i);
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content" + i + "\n"));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_fragment) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
        newsAdapter.setTwoPane(isTwoPane);
    }

}