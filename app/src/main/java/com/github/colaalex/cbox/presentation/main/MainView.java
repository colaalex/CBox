package com.github.colaalex.cbox.presentation.main;

import com.arellomobile.mvp.MvpView;
import com.github.colaalex.cbox.domain.entity.Post;

import java.util.List;

public interface MainView extends MvpView {

    void addPost(Post post);
    void showProgressBar(boolean isLoading);
    void showMessage(String msg);
    void showPost(Post post);
    void addPosts(List<Post> posts);
    void createPost();
}
