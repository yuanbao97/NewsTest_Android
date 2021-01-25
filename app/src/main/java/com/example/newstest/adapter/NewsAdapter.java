package com.example.newstest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newstest.R;
import com.example.newstest.avtivity.NewsContentActivity;
import com.example.newstest.fragment.NewsContentFragment;
import com.example.newstest.framework.BaseAdapter;
import com.example.newstest.response.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter<News, NewsAdapter.ViewHolder> {

    private FragmentActivity mContext;

    public void setTwoPane(boolean twoPane) {
        isTwoPane = twoPane;
        notifyDataSetChanged();
    }

    private boolean isTwoPane;

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(News news) {
            TextView newsTitleText = (TextView) itemView.findViewById(R.id.news_title);
            newsTitleText.setText(news.getTitle());
        }
    }



    public NewsAdapter(List datas, FragmentActivity context) {
        super(datas);
        mContext = context;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News news = datas.get(holder.getAdapterPosition());
                if(isTwoPane) {
                    mContext.getFragmentManager();
                    NewsContentFragment newsContentFragment = (NewsContentFragment) mContext.getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
                    newsContentFragment.refresh(news.getTitle(), news.getContent());
                } else {
                    Intent intent = new Intent(mContext, NewsContentActivity.class);
                    intent.putExtra("newsTitle", news.getTitle());
                    intent.putExtra("newsContent", news.getContent());
                    mContext.startActivity(intent);
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
