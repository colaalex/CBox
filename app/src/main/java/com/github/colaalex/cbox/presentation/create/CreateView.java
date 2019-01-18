package com.github.colaalex.cbox.presentation.create;

import com.arellomobile.mvp.MvpView;
import com.github.colaalex.cbox.domain.entity.Post;

public interface CreateView extends MvpView {
    void postAdded(Post post);
    void addFailed();
}
