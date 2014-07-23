/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true,tagName = "col")
public class Column extends UIComponentBase {
    
    @Override
    public String getFamily() {
        return "test";
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String value = (String) getAttributes().get("value");
        String style = (String) getAttributes().get("style");
//        if (value != null) {
            ResponseWriter responseWriter = context.getResponseWriter();
            responseWriter.startElement("p", null);
            responseWriter.writeAttribute("style", style, null);
            responseWriter.writeText(value, null);
            responseWriter.endElement("p");
            
            
           
//        }
    }
    
}
