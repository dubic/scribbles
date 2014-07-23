/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.html.HtmlInputText;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "inputText")
public class UInputText extends HtmlInputText {

    public UInputText() {
        super();
    }
    
    
    @Override
    public String getFamily() {
        return "test";
    }
    
}
