/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.posts;

/**
 *
 * @author dubem
 */
public class PostException extends Exception {

    /**
     * Creates a new instance of <code>PostException</code> without detail
     * message.
     */
    public PostException() {
    }

    /**
     * Constructs an instance of <code>PostException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PostException(String msg) {
        super(msg);
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
