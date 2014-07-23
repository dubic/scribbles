/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.spi;

/**
 *
 * @author dubem
 */
public class RoleServiceException extends Exception{

    public RoleServiceException() {
    }

    public RoleServiceException(String message) {
        super(message);
    }

    public RoleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
