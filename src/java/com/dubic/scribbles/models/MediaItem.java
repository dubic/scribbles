/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * depicting uploaded media items in the posts
 *
 * @author dubem
 */
@Entity
@Table(name = "media")
public class MediaItem implements Serializable {

    private Long id;
    private String title;
    private String duration;
    private String filesize;
    private MediaItem.MIME_TYPE mimeType;
    private int views;
    private Date lastUpdated = new Date();

    public enum MIME_TYPE {

        IMAGE, VIDEO
    }

    public MediaItem() {
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

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "duration")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Column(name = "file_size")
    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mime_type")
    public MIME_TYPE getMimeType() {
        return mimeType;
    }

    public void setMimeType(MIME_TYPE mimeType) {
        this.mimeType = mimeType;
    }

    @Column(name = "views")
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Column(name = "updated")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
