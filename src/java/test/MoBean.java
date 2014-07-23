/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

/**
 *
 * @author dubem
 */
@ManagedBean
@RequestScoped
public class MoBean {
//private String 

    private List<Person> persons = new ArrayList<Person>();
    private HtmlCommandButton btn;
    private UIScript script;
    private String joke;

    private String nullTest;

    //<editor-fold defaultstate="collapsed" desc="getter setter">
    public String getNullTest() {
        return nullTest;
    }

    public void setNullTest(String nullTest) {
        this.nullTest = nullTest;
    }

    public HtmlCommandButton getBtn() {
        return btn;
    }

    public void setBtn(HtmlCommandButton btn) {
        this.btn = btn;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public UIScript getScript() {
        return script;
    }

    public void setScript(UIScript script) {
        this.script = script;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
//</editor-fold>

    /**
     * Creates a new instance of MoBean
     */
    public MoBean() {
        persons.add(new Person(1, "john eyo", "code optimization is still going on. Most of the pages have been made"));
        persons.add(new Person(2, "edmund eyo", "These events can trigger transitions to other states that result"));
        persons.add(new Person(3, "remi olukanmbi", "I've seen too many people get burnt out from buying"));
    }

    public void testWriter() {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println("u pressed id " + map.get("id"));
        script.setPayload(new Gson().toJson(persons.get(Integer.parseInt(map.get("id")) - 1)));
    }

    public void validate(FacesContext fc, UIComponent comp, Object object) {
        System.out.println("validated");
        Object value = comp.getValueExpression("value").getValue(fc.getELContext());
        System.out.println("value = " + object);
        System.out.println("valText " + nullTest);
        if (object == null) {
            fc.addMessage(comp.getClientId(), new FacesMessage("Please Enter a valuable value"));
            throw new ValidationException();
        }
    }

    public void postJoke() {

        PostData p = new PostData();
        p.setIconUrl("assets/img/avatar1.jpg");
        p.setId(1L);
        p.setLikes(5);
        p.setPost(joke);
        p.setPostedTime(Calendar.getInstance().toString());
        p.setPoster("john may");
        script.setPayload(new Gson().toJson(p));
    }
}
