package com.github.colaalex.cbox.presentation.post;

import com.arellomobile.mvp.MvpView;
import com.github.colaalex.cbox.domain.entity.Comment;

import java.util.List;

public interface PostView extends MvpView {

    void showProgressBar(boolean isLoading);
    void showComments(List<Comment> comments);
    void showError(String msg);
}
