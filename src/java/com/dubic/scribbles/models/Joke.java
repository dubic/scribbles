/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.models;

import com.dubic.scribbles.idm.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author dubem
 */
@Entity
//@EntityListeners(BankAccountListener.class)
@Table(name = "joke")
public class Joke extends Post implements Serializable {

    private List<MediaItem> mediaItems = new ArrayList<MediaItem>();

    public Joke() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "posted message is empty")
    @Lob
    @Column(name = "post", nullable = false)
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @NotNull(message = "you must be logged in to post a joke")
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @NotNull(message = "posted date must be set")
    @Column(name = "posted_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Calendar getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Calendar postedTime) {
        this.postedDate = postedTime;
    }

    @NotNull(message = "posted time must be set")
    @Column(name = "posted_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    public Date getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
    }

    @OneToMany
    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    @Column(name = "blocked")
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Column(name = "num_reads")
    public int getReads() {
        return reads;
    }

    public void setReads(int reads) {
        this.reads = reads;
    }

    @Column(name = "likes")
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Column(name = "dislikes")
    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edited")
    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

}
