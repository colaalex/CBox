package com.github.colaalex.cbox.data.repository;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.data.api.PostApi;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Repository implements IPostRepository {

    private PostApi postApi;

    @Inject
    public Repository(PostApi postApi) {
        this.postApi = postApi;
    }

    @Override
    public void getPost(ApiCallback callback) {
        postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

    @Override
    public void getComments(int postId, ApiCallback callback) {
        postApi.getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSuccess, callback::onError);
    }

}
