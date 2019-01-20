package com.github.colaalex.cbox.domain.interactor;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class CommentInteractor {

    private IPostRepository postRepository;

    @Inject
    CommentInteractor(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Disposable getComments(int postId, ApiCallback callback) {
        return postRepository.getComments(postId, callback);
    }
}
