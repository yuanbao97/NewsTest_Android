package com.example.newstest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.newstest.R;

public class NewsContentFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_content, container, false);
        return view;
    }

    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleTextView = (TextView) view.findViewById(R.id.news_title_text_view);
        TextView newsContentTextView = (TextView) view.findViewById(R.id.news_content_text_view);
        newsTitleTextView.setText(newsTitle);
        newsContentTextView.setText(newsContent);
    }
}