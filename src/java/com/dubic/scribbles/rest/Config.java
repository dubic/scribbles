/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author dubem
 */
public class Config extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public Config () {
        register(SimpleResource.class);
    }
}
