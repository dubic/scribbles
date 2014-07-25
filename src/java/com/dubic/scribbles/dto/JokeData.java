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
    private List<CommentData> comments = new ArrayList<CommentData>();

    public JokeData(Joke joke) {
        this.id = joke.getId();
        this.post = joke.getPost();
        this.blocked = joke.isBlocked();
        this.likes = joke.getLikes();
        this.dislikes = joke.getDislikes();
        this.imageURL = "p/"+joke.getUser().getProfile().getPicture();
        this.duration = IdmUtils.convertPostedTime(joke.getEditedDate().getTime());
    }
    
    
}
