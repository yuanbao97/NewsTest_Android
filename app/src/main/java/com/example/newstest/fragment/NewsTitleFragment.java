package com.example.newstest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstest.R;
import com.example.newstest.adapter.NewsAdapter;
import com.example.newstest.avtivity.NewsContentActivity;
import com.example.newstest.framework.BaseAdapter;
import com.example.newstest.response.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        RecyclerView newsTitleRecycleView = (RecyclerView) view.findViewById(R.id.news_title_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
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
    }

    public class NewsAdapter extends BaseAdapter<News, NewsAdapter.ViewHolder> {

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }

            public void setData(News news) {
                TextView newsTitleText = (TextView) itemView.findViewById(R.id.news_title_text_view);
                newsTitleText.setText(news.getTitle());
            }
        }

        public NewsAdapter(List datas) {
            super(datas);
        }

        @Override
        protected boolean areItemsTheSame(News oldItem, News newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        protected boolean areContentsTheSame(News oldItem, News newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = datas.get(holder.getAdapterPosition());
                    if(isTwoPane) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        Intent intent = new Intent(getContext(), NewsContentActivity.class);
                        intent.putExtra("newsTitle", news.getTitle());
                        intent.putExtra("newsContent", news.getContent());
                        startActivity(intent);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = datas.get(position);
            holder.setData(news);
        }
    }
}