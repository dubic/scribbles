/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.faces;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "script")
public class Script extends UIComponentBase {

    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String getFamily() {
        return "test";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        
        String method = (String) getAttributes().get("value");
        if (this.payload != null) {
            ResponseWriter responseWriter = context.getResponseWriter();

            responseWriter.startElement("script", null);
            responseWriter.writeText("$(function(){", null);//});
            responseWriter.writeText(method + "(" + this.payload + ");", null);
            responseWriter.writeText("});", null);
            responseWriter.endElement("script");
        }

    }

}
