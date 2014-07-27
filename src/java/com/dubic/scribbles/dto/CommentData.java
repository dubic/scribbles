/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.dto;

import com.dubic.scribbles.models.JKComment;
import com.dubic.scribbles.utils.IdmUtils;

/**
 *
 * @author dubem
 */
public class CommentData {
    private Long id;
    private String text;
    private String duration;
    private String imageURL;
    private String poster;
    
    public CommentData(JKComment c) {
        this.id = c.getId();
        this.text = c.getText();
        this.duration = IdmUtils.convertPostedTime(c.getPostedTime().getTimeInMillis());
        this.imageURL = "p/"+c.getUser().getProfile().getPicture();
        this.poster = c.getUser().getScreenName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    
}
