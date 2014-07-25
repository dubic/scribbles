/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.models;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author dubem
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    private Long id;
    private String text;
    private Calendar postedTime = Calendar.getInstance();
    private Joke joke;

    public Comment() {
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

    @NotEmpty(message = "comment must not be a null value")
    @Lob
    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "posted")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Calendar getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(Calendar postedTime) {
        this.postedTime = postedTime;
    }

    @ManyToOne
    @JoinColumn(name = "joke_id")
    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

}
