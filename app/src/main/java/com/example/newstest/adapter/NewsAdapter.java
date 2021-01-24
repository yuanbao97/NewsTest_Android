package com.example.newstest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newstest.R;
import com.example.newstest.framework.BaseAdapter;
import com.example.newstest.response.News;

import java.util.List;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = datas.get(holder.getAdapterPosition());
                //
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
