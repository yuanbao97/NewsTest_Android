package com.example.newstest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.newstest.R;
import com.example.newstest.avtivity.NewsContentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsTitleFragment extends Fragment {

    @BindView(R.id.to_news_content_activity_button)
    Button toNewsContentActivityButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @OnClick(R.id.to_news_content_activity_button)
    public void onClick() {
        startActivity(new Intent(getActivity(), NewsContentActivity.class));
    }

}