/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.models;

import com.dubic.scribbles.idm.models.User;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dubem
 */

public abstract class Post {
    protected Long id;
    protected String post;
    protected User user;
    protected String iconUrl;
    protected Calendar postedDate = Calendar.getInstance();
    protected Date postedTime = new Date();
    protected boolean blocked;
    protected int likes = 0;
    protected int dislikes = 0;
    protected Date editedDate = new Date();
}
