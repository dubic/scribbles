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
@FacesComponent(createTag = true, tagName = "message")
public class Message extends UIComponentBase {

    @Override
    public String getFamily() {
        return "com.dubic.faces";
    }

    public void setType(String type) {
        getStateHelper().put(PropertyKeys.type, type);
    }

    public String getType() {
        return (String) getStateHelper().eval(PropertyKeys.type);
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        String summary = (String) getAttributes().get("summary");
        List<FacesMessage> messageList = context.getMessageList();
        if (summary == null && messageList.isEmpty()) {
            return;
        }
        String styleClass = (String) getAttributes().get("styleClass");
        String cls = "alert ";
        String style = (String) getAttributes().get("style");
        String type = (String) getAttributes().get("type");
        String dismiss = (String) getAttributes().get("dismiss");

        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", null);
///////////////HANDLE TYPE////////////////
        
        if ("success".equals(type)) {
            cls = cls + "alert-success ";
        }
        if ("danger".equals(type)) {
            cls = cls + "alert-danger ";
        }
        if ("info".equals(type)) {
            cls = cls + "alert-info ";
        }
        if (type == null) {
            cls = cls + getClassTypeBySeverity(messageList);
        }
        
        if (dismiss != null) {
            if (Boolean.valueOf(dismiss)) {
                cls = cls + "alert-dismissable ";
            }
        }
        if (styleClass != null) {
            cls = cls + styleClass + " ";
        }
///////////////HANDLE CLASS////////////////
        writer.writeAttribute("class", cls, null);

        if (style != null) {
            writer.writeAttribute("style", style, null);
        }
        ///////////////ADD DISMISS BUTTON////////////////
        if (dismiss != null) {
            if (Boolean.valueOf(dismiss)) {
                writer.startElement("button", null);
                writer.writeAttribute("class", "close", null);
                writer.writeAttribute("data-dismiss", "alert", null);
                writer.writeAttribute("aria-hidden", "true", null);
                writer.endElement("button");
            }
        }
        if (summary != null) {
            renderSummary(writer, summary);
        } else {
            for (FacesMessage facesMessage : messageList) {
                renderSummary(writer, facesMessage.getSummary());
            }
        }

    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        String summary = (String) getAttributes().get("summary");
        List<FacesMessage> messageList = context.getMessageList();
        if (summary == null && messageList.isEmpty()) {
            return;
        }
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }

    @Override
    public void encodeChildren(FacesContext context) throws IOException {
        String summary = (String) getAttributes().get("summary");
        List<FacesMessage> messageList = context.getMessageList();
        if (summary == null && messageList.isEmpty()) {
            return;
        }
        super.encodeChildren(context); //To change body of generated methods, choose Tools | Templates.

    }

    private void renderSummary(ResponseWriter writer, String summary) throws IOException {
        writer.startElement("p", null);
        writer.writeText(summary, null);
        writer.endElement("p");
    }

    private String getClassTypeBySeverity(List<FacesMessage> messageList) {
        for (FacesMessage facesMessage : messageList) {
                if(facesMessage.getSeverity().equals(FacesMessage.SEVERITY_ERROR)){
                    return "alert-danger ";
                }
            }
        return "alert-success ";
    }

    

    protected static enum PropertyKeys {

        type,
        dismiss,
        summary,
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
