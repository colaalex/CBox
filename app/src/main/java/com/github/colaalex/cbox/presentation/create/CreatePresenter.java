package com.github.colaalex.cbox.presentation.create;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.entity.Post;
import com.github.colaalex.cbox.domain.interactor.CreateInteractor;

import javax.inject.Inject;

@InjectViewState
public class CreatePresenter extends MvpPresenter<CreateView> {

    private CreateInteractor intercator;

    @Inject
    CreatePresenter(CreateInteractor interactor) {
        this.intercator = interactor;
    }

    void sendPost(String title, String text) {
        Log.d("Create Presenter", text);
        intercator.sendPost(title, text, new ApiCallback() {
            @Override
            public void onSuccess(Object result) {
                Log.d("Create presenter", "Add successful");
                if (result instanceof Post) {
                    getViewState().postAdded((Post) result);
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.d("Create presenter", "Add failed");
                getViewState().addFailed();
            }
        });
    }
}
