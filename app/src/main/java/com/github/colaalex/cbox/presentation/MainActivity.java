package com.github.colaalex.cbox.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Inject
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mainPresenter.insertPosts();
    }

    @Override
    public void addPost(Post post) {
        adapter.addPost(post);
    }
}
