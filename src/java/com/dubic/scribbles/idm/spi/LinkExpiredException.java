/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.spi;

/**
 *
 * @author dubem
 */
public class LinkExpiredException extends IdentityServiceException{

    public LinkExpiredException() {
    }

    public LinkExpiredException(String message) {
        super(message);
    }

    public LinkExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
