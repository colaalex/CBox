package com.github.colaalex.cbox.presentation.main;

import com.arellomobile.mvp.MvpView;
import com.github.colaalex.cbox.domain.entity.Post;

public interface MainView extends MvpView {

    void addPost(Post post);
    void showProgressBar(boolean isLoading);
    void showError(String msg);
    void showPost(int pos);
}
