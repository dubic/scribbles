/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.faces;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "formGroup")
public class FormGroup extends UIComponentBase {

    @Override
    public String getFamily() {
        return "com.dubic.scribbles.faces";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        String icon = (String) getAttributes().get(PropertyKeys.icon.toString());

        String styleClass = (String) getAttributes().get(PropertyKeys.styleClass.toString());
        String cls = "form-group ";
        String style = (String) getAttributes().get(PropertyKeys.style.toString());

        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", null);
///////////////HANDLE CLASS////////////////
        if (styleClass != null) {
            cls = cls + styleClass + " ";
        }
        writer.writeAttribute("class", cls, null);

        if (style != null) {
            writer.writeAttribute("style", style, null);
        }
        ///////////////ADD icons////////////////
        if (icon != null) {
            writer.startElement("div", null);
            writer.writeAttribute("class", "input-icon", null);
            writer.startElement("i", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
        }

    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
        writer.endElement("div");
    }

    @Override
    public void encodeChildren(FacesContext context) throws IOException {
        super.encodeChildren(context); //To change body of generated methods, choose Tools | Templates.

    }

    protected static enum PropertyKeys {

        icon,
        style,
        styleClass;

        String toString;

        private PropertyKeys(String toString) {
            this.toString = toString;
        }

        private PropertyKeys() {
        }

        @Override
        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }

    }
}
