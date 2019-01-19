package com.github.colaalex.cbox.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.github.colaalex.cbox.presentation.RecyclerViewClickListener;
import com.github.colaalex.cbox.presentation.create.CreateActivity;
import com.github.colaalex.cbox.presentation.post.PostActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

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

    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createRecycler();

        presenter.loadPosts();
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
    public void showMessage(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showPost(Post post) {
        Intent intent = new Intent(getApplicationContext(), PostActivity.class);
        intent.putExtra("POST", post);
        startActivity(intent);
    }

    @Override
    public void addPosts(List<Post> posts) {
        adapter.addPosts(posts);
    }

    @Override
    public void createPost() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            Post post = (Post) getIntent().getSerializableExtra("Post");
            if (post != null) {
                addPost(post);
            }
            showMessage("New post created");
        } else if (resultCode == 2)
            showMessage("Couldn't create post");
    }

    private void createRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerViewClickListener listener = (view, position, post) -> {
            if (position == 0) {
                presenter.createPost();
            }
            else
                presenter.showPost(post);
        };
        adapter = new MainAdapter(listener);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
