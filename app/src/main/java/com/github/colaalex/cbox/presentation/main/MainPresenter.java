package com.github.colaalex.cbox.presentation.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.interactor.PostInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private PostInteractor interactor;

    private CompositeDisposable compositeDisposable;

    @Inject
    MainPresenter(PostInteractor postInteractor) {
        this.interactor = postInteractor;
        this.compositeDisposable = new CompositeDisposable();
    }

    void loadPosts() {
        getViewState().showProgressBar(true);
        compositeDisposable.add(interactor.getPosts(new ApiCallback() {
            @Override
            public void onSuccess(Object result) {
                if (result instanceof List) {
                    getViewState().addPosts((List<Post>) result);
                }
                getViewState().showProgressBar(false);
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showMessage("Error loading posts");
                getViewState().showProgressBar(false);
            }
        }));
    }

    void createPost() {
        getViewState().createPost();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    void showPost(Post post) {
        getViewState().showPost(post);
    }
}
