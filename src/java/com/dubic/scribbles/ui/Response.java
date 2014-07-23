/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.ui;

import com.dubic.scribbles.models.Comment;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author dubem
 */
public class Response {
    private Object data;
    private Set<ConstraintViolation<Comment>> constraintViolations;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Set<ConstraintViolation<Comment>> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(Set<ConstraintViolation<Comment>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
    
    
}
