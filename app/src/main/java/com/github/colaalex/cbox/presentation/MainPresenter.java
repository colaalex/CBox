package com.github.colaalex.cbox.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.interactor.PostInteractor;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private PostInteractor postInteractor;

    @Inject
    public MainPresenter(PostInteractor postInteractor) {
        this.postInteractor = postInteractor;
    }

    public void insertPosts() {
        for (int i = 1; i < 6; i++) {
            postInteractor.getPost(i, new ApiCallback() {
                @Override
                public void onSuccess(Post post) {
                    getViewState().addPost(post);
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

}
