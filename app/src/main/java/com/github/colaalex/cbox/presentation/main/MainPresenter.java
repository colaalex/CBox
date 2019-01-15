package com.github.colaalex.cbox.presentation.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.interactor.PostInteractor;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private PostInteractor interactor;

    private boolean isLoading;

    @Inject
    MainPresenter(PostInteractor postInteractor) {
        this.interactor = postInteractor;
        isLoading = false;
    }

    void loadPosts() {
        isLoading = true;
        getViewState().showProgressBar(isLoading);
        interactor.getPosts(new ApiCallback() {
            @Override
            public void onSuccess(Object result) {
                if (result instanceof List) {
                    getViewState().addPosts((List<Post>) result);
                }
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showError("Error loading posts");
            }
        });
    }

    private void stopLoading() {
        isLoading = false;
        getViewState().showProgressBar(isLoading);
    }

    public boolean isLoading() {
        return isLoading;
    }

    void showPost(int pos) {
        getViewState().showPost(pos);
    }
}
