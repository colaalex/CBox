package com.github.colaalex.cbox.presentation.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.interactor.PostInteractor;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final int PAGE_SIZE = 5;

    private PostInteractor interactor;

    private boolean isLoading;
    private int requested;

    private int pageNumber;

    @Inject
    MainPresenter(PostInteractor postInteractor) {
        this.interactor = postInteractor;
        isLoading = false;
        requested = 0;
        pageNumber = 0;
    }

    void loadPosts() {
        if (pageNumber * PAGE_SIZE >= 100)
            return;
        isLoading = true;
        getViewState().showProgressBar(isLoading);
        for (int i = PAGE_SIZE * pageNumber + 1; i < PAGE_SIZE * pageNumber + 6; i++) {
            requested++;
            interactor.getPost(i, new ApiCallback() {
                @Override
                public void onSuccess(Object result) {
                    getViewState().addPost((Post) result);
                    if (--requested == 0) {
                        stopLoading();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                    getViewState().showError("Error loading posts");
                    if (--requested == 0) {
                        stopLoading();
                    }
                }
            });
        }
        pageNumber++;
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
