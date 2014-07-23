/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.ui;

import com.dubic.scribbles.idm.spi.IdentityServiceImpl;
import com.dubic.scribbles.ui.validators.EmailValidator;
import com.dubic.scribbles.utils.IdmUtils;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author dubem
 */
@FacesValidator("regValidator")
public class RegistrationValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput textInput = (UIInput) component;
        if ("screeName".equals(textInput.getClientId())) {
            validateRange((String) value, 4, 20);
            validateScreenNameExists((String) value, context);
        }
        if ("email".equals(textInput.getClientId())) {
            validateEmailSyntax((String) value);
            validateEmailExists((String) value, context);
        }
        if ("fpemail".equals(textInput.getClientId())) {
            validateEmailSyntax((String) value);
        }
        if ("password".equals(textInput.getClientId())) {
            validatePassword((String) value, context, textInput);
        }
        if ("vpassword".equals(textInput.getClientId())) {
            validatePasswordVerification((String) value, context, textInput);
        }
        if ("fppassword".equals(textInput.getClientId())) {
            validatePassword((String) value, context, textInput);
        }
    }

    private void validateRange(String value, int min, int max) {
//        String value = (String)textInput.getValue();
        if (value.length() >= min && value.length() <= max) {
            return;
        }
//        ((HtmlInputText)textInput).gets
        throw new ValidatorException(new FacesMessage(String.format("must be between %d and %d characters", min, max)));
    }

    private void validateScreenNameExists(String value, FacesContext fc) {
        IdentityServiceImpl id = FacesContextUtils.getRequiredWebApplicationContext(fc).getBean(IdentityServiceImpl.class);
        if (id.validateScreenName(value) != null) {
            throw new ValidatorException(new FacesMessage(String.format("%s already exists", value)));
        }
    }

    private void validateEmailExists(String value, FacesContext fc) {
        IdentityServiceImpl id = FacesContextUtils.getRequiredWebApplicationContext(fc).getBean(IdentityServiceImpl.class);
        if (id.getUniqueEmail((String) value) != null) {
            throw new ValidatorException(new FacesMessage(String.format("%s already exists", value)));
        }
    }

    private void validateEmailSyntax(String value) {
        if (!EmailValidator.getInstance(true).isValid(value)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format("%s not a valid Email", value), null));
        }
    }

    private void validatePassword(String password, FacesContext context, UIInput textInput) {

        if (IdmUtils.isEmpty(password)) {
            throw new ValidatorException(new FacesMessage("Empty password not accepted"));
        }
        if (password.length() < 8) {
            throw new ValidatorException(new FacesMessage("Must be more than 8 characters"));
        }
    }

    private void validatePasswordVerification(String vpass, FacesContext context, UIInput textInput) {
        if (IdmUtils.isEmpty(vpass)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
        String password = (String) textInput.getAttributes().get("pword");
        System.out.println("pass>>>" + password);
        if (!vpass.equals(password)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
    }

}
