package com.github.colaalex.cbox.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.colaalex.cbox.App;
import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.domain.entity.Post;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter mainPresenter;

    @Inject
    Provider<MainPresenter> presenterProvider;

    @ProvidePresenter
    MainPresenter providePresenter() {
        return presenterProvider.get();
    }

    @BindView(R.id.rvPosts)
    RecyclerView recyclerView;
    @BindView(R.id.pbLoading)
    ProgressBar progressBar;

    @Inject
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createRecycler();

        mainPresenter.loadPosts();
    }

    @Override
    public void addPost(Post post) {
        adapter.addPost(post);
    }

    @Override
    public void showProgressBar(boolean isLoading) {
        if (isLoading)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void createRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!mainPresenter.isLoading()) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount)
                        mainPresenter.loadPosts();
                }
            }
        });
    }
}
