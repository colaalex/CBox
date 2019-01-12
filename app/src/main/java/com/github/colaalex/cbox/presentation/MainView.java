package com.github.colaalex.cbox.presentation;

import com.arellomobile.mvp.MvpView;
import com.github.colaalex.cbox.domain.entity.Post;

public interface MainView extends MvpView {

    void addPost(Post post);
}
