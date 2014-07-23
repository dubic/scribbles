/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.posts;

import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.models.Post;
import com.dubic.scribbles.models.Tag;
import com.dubic.scribbles.ui.Response;
import javax.persistence.PersistenceException;

/**
 * interface for all posts
 *
 * @author dubem
 */
public interface PostService {

    public Post savePost(String post) throws PersistenceException,PostException;

    public Post updatePost(Post post) throws PersistenceException;

    public void blockPost(Long postId) throws PersistenceException;

    public void watchTag(User user, Tag tag) throws PersistenceException;

    public void removeWatch(Long id) throws PersistenceException;

    public void like(Post post) throws PersistenceException;

    public void dislike(Post post) throws PersistenceException;
    
    public int numOfPosts(User user);

}
