package com.github.colaalex.cbox.presentation.post;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.colaalex.cbox.R;
import com.github.colaalex.cbox.di.App;
import com.github.colaalex.cbox.domain.entity.Comment;
import com.github.colaalex.cbox.domain.entity.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends MvpAppCompatActivity implements PostView {

    @InjectPresenter
    PostPresenter presenter;

    @Inject
    Provider<PostPresenter> presenterProvider;

    @BindView(R.id.rvPosts)
    RecyclerView recyclerView;
    @BindView(R.id.pbLoading)
    ProgressBar progressBar;
    @BindView(R.id.tbMain)
    Toolbar toolbar;

    private PostAdapter adapter;

    @ProvidePresenter
    PostPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null) throw new AssertionError();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Post post = (Post) getIntent().getSerializableExtra("POST");
        int postId = post.getPostId();
        adapter = new PostAdapter(new ArrayList<>(), post);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.getComments(postId);
    }

    @Override
    public void showProgressBar(boolean isLoading) {
        if (isLoading)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showComments(List<Comment> comments) {
        adapter.loadComments(comments);
    }

    @Override
    public void showError(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
