package com.github.colaalex.cbox.domain.interactor;

import com.github.colaalex.cbox.data.api.ApiCallback;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import javax.inject.Inject;

public class CommentInteractor {

    private IPostRepository postRepository;

    @Inject
    CommentInteractor(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void getComments(int postId, ApiCallback callback) {
        postRepository.getComments(postId, callback);
    }
}
