/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.idm.spi;

/**
 *
 * @author dubem
 */
public class InvalidTokenException extends IdentityServiceException {

    /**
     * Creates a new instance of <code>InvalidTokenException</code> without
     * detail message.
     */
    public InvalidTokenException() {
    }

    /**
     * Constructs an instance of <code>InvalidTokenException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidTokenException(String msg) {
        super(msg);
    }
}
