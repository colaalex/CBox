package com.github.colaalex.cbox.presentation.post;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Comment;
import com.github.colaalex.cbox.domain.interactor.CommentInteractor;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class PostPresenter extends MvpPresenter<PostView> {

    private CommentInteractor interactor;

    @Inject
    PostPresenter(CommentInteractor interactor) {
        this.interactor = interactor;
        PostView view = getViewState();
        Log.d("PostPresenter", view.toString());
    }

    void getComments(int postId) {
        getViewState().showProgressBar(true);
        interactor.getComments(postId, new ApiCallback() {
            @Override
            public void onSuccess(Object result) {
                getViewState().showProgressBar(false);
                getViewState().showComments((List<Comment>) result);  //TODO safe casting
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showError("Can't load comments");
            }
        });
    }
}
