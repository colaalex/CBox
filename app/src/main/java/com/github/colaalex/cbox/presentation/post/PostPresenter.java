package com.github.colaalex.cbox.presentation.post;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Comment;
import com.github.colaalex.cbox.domain.interactor.CommentInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@InjectViewState
public class PostPresenter extends MvpPresenter<PostView> {

    private CommentInteractor interactor;

    private CompositeDisposable compositeDisposable;

    @Inject
    PostPresenter(CommentInteractor interactor) {
        this.interactor = interactor;
        this.compositeDisposable = new CompositeDisposable();
    }

    void getComments(int postId) {
        getViewState().showProgressBar(true);
        compositeDisposable.add(interactor.getComments(postId, new ApiCallback() {
            @Override
            public void onSuccess(Object result) {
                getViewState().showProgressBar(false);
                getViewState().showComments((List<Comment>) result);
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showError("Can't load comments");
            }
        }));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
