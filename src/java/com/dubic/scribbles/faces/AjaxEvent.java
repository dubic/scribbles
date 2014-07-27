/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.faces;

import java.io.IOException;
import javax.annotation.Resource;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true,tagName = "ajaxEvent")
@ResourceDependency(name = "jsf.js",library = "javax.faces",target = "head")
public class AjaxEvent extends UIComponentBase {
    
    @Override
    public String getFamily() {
        return "com.dubic.scribbles.faces";
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String value = (String) getAttributes().get("value");
        if (value != null) {
            ResponseWriter writer = context.getResponseWriter();
            writer.startElement("script", null);
            writer.writeText("$(function(){", null);//});
            writer.writeText("jsf.ajax.request(this, event,{execute:@this,render:@this});", null);//});
            writer.writeText("});", null);
            writer.endElement("script");
        }
    }
    
}
