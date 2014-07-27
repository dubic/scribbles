/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.ui;

import com.dubic.scribbles.models.JKComment;
import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author dubem
 */
public class Response {
    private Object data;
    private Set<ConstraintViolation<JKComment>> constraintViolations;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Set<ConstraintViolation<JKComment>> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(Set<ConstraintViolation<JKComment>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
    
    
}
