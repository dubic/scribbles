/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.spi;

/**
 *
 * @author dubem
 */
public class IdentityServiceException extends Exception{

    public IdentityServiceException() {
    }

    public IdentityServiceException(String message) {
        super(message);
    }

    public IdentityServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
