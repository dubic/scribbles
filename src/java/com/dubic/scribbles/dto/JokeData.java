/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.dto;

import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.models.Joke;
import com.dubic.scribbles.utils.IdmUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dubem
 */
public class JokeData {
    private Long id;
    private String post;
    private boolean blocked;
    private int likes = 0;
    private int dislikes = 0;
    private String imageURL;
    private String duration;
    private String poster;
    private List<CommentData> comments = new ArrayList<CommentData>();

    public JokeData(Joke joke) {
        this.id = joke.getId();
        this.post = joke.getPost();
        this.blocked = joke.isBlocked();
        this.likes = joke.getLikes();
        this.dislikes = joke.getDislikes();
        this.poster = joke.getUser().getScreenName();
        this.imageURL = "p/"+joke.getUser().getProfile().getPicture();
        this.duration = IdmUtils.formatDate(joke.getEditedDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<CommentData> getComments() {
        return comments;
    }

    public void setComments(List<CommentData> comments) {
        this.comments = comments;
    }
    
    
}
