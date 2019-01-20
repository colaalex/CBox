package com.github.colaalex.cbox.domain.interactor;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class PostInteractor {

    private IPostRepository postRepository;

    @Inject
    PostInteractor(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Disposable getPosts(ApiCallback callback) {
        return postRepository.getPost(callback);
    }
}
