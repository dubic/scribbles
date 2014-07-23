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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dubem
 */
@Entity
@Table(name = "profile")
public class Profile implements Serializable {

    private Long id;
    private String picture;
    private Long jokes = 0L;//post stats
    private Long proverbs = 0L;//post stats
    private Long quotes = 0L;//post stats
//    private List<Watch> watches;
    private Date updated = new Date();

    public Profile() {
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

    @Column(name = "pix")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(name = "jokes")
    public Long getJokes() {
        return jokes;
    }

    public void setJokes(Long jokes) {
        this.jokes = jokes;
    }

    @Column(name = "proverbs")
    public Long getProverbs() {
        return proverbs;
    }

    public void setProverbs(Long proverbs) {
        this.proverbs = proverbs;
    }

    @Column(name = "quotes")
    public Long getQuotes() {
        return quotes;
    }

    public void setQuotes(Long quotes) {
        this.quotes = quotes;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "updated")
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
