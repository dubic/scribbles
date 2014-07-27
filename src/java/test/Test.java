/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import com.dubic.scribbles.models.JKComment;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.apache.catalina.tribes.util.ExecutorFactory;

/**
 *
 * @author dubem
 */
public class Test {
    public static void main(String[] jhdd){
        JKComment joke = new JKComment();
        joke.setText("");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<JKComment>> results = factory.getValidator().validate(joke);
        for (ConstraintViolation<JKComment> constraintViolation : results) {
            System.out.println("field value >> "+constraintViolation.getPropertyPath());
            System.out.println("message >> "+constraintViolation.getMessage());
        }
        
        
    }
}
