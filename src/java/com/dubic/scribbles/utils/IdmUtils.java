/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.utils;

import com.dubic.scribbles.idm.models.Role;
import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.models.Comment;
import com.dubic.scribbles.models.Joke;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * performs all idm utilities
 *
 * @author dubic
 * @since idm 1.0.0
 */
public class IdmUtils {

    public static void tokenize(String pathInfo, String delim) {
        StringTokenizer t = new StringTokenizer(pathInfo, delim);
        while (t.hasMoreElements()) {
            System.out.println("token - " + t.nextToken());

        }

    }

    public static Date getActivationTokenExpiryDt() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static String generateTimeToken() {
        char[] time = String.valueOf(System.currentTimeMillis()).toCharArray();
        List<char[]> charrList = new ArrayList<char[]>();
        charrList.add(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'});
        charrList.add(new char[]{'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'});
        charrList.add(new char[]{'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3'});
        charrList.add(new char[]{'4', '5', '6', '7', '8', '9'});
        int ap = new Random().nextInt(charrList.size());
        StringBuffer tokenBuf = new StringBuffer();
        for (int i = 0; i < time.length; i++) {
            ap = ap >= charrList.size() ? 0 : ap;
            int chPos = Character.digit(time[i], Character.MAX_RADIX);
            if(chPos >= charrList.get(ap).length){
                tokenBuf.append(chPos);
            }
            else{
                tokenBuf.append(charrList.get(ap)[chPos]);
            }
            ap++;
        }
        return tokenBuf.toString();
    }

    public static Date getPwordResetTokenExpiryDt() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 15);
        return cal.getTime();
    }

    private final Logger log = Logger.getLogger(getClass());
    public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * return the first value of the list or null if list is empty
     *
     * @param list
     * @return
     */
    public static <T> T getFirstOrNull(List<T> list) {
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static boolean isEmpty(String str) {
        return (str == null) || (str.trim().length() <= 0);
    }

    public static String formatMoney(double amt) {
        return new DecimalFormat("###,###,###,###.00").format(amt);
    }

    public static Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            grantedAuths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuths;
    }

    public static String getUserEmailLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = null;
        try {
            principal = auth.getPrincipal();
        } catch (NullPointerException e) {
            return null;
        }
        if (principal == null) {
            return null;
        }
        return (String) principal;
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a").format(date);
        } catch (Exception e) {
//            log.warn("ERROR FORMATING DATE - " + e.getMessage());
        }
        return null;
    }

    public static Date parseDate(String date) {
        if (isEmpty(date)) {
            return null;
        }
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException ex) {

        }
        return null;
    }

    public static String convertPostedTime(Long postedTime) {
        long now = new Date().getTime();
        long postedTimeDiff = now - postedTime;
        long secs = postedTimeDiff / 1000;
        int mins = (int) (secs / 60);
        int hrs = mins / 60;
        int days = hrs / 24;
        int years = days / 365;
        StringBuilder sb = new StringBuilder();
        if (days > 0 && years > 0) {
            sb.append("about ").append(years).append(" year(s), ").append(days).append(" day(s) ago");
            return sb.toString();
        }
        if (years > 0) {
            sb.append("about ").append(years).append(" year(s) ago");
            return sb.toString();
        }
        if (days > 0) {
            sb.append("about ").append(days).append(" day(s) ago");
            return sb.toString();
        }
        if (hrs > 0) {
            sb.append("about ").append(hrs).append(" hour(s) ago");
            return sb.toString();
        }
        if (mins > 0) {
            sb.append("about ").append(mins).append(" minute(s) ago");
            return sb.toString();
        }
        sb.append("just now");
        return sb.toString();
    }

    public static <T> void validate(T t, Class<?>... types) throws ConstraintViolationException {
        Set<ConstraintViolation<T>> results = validator.validate(t);
        if (!results.isEmpty()) {
            throw new ConstraintViolationException("Failed to validate Object of class " + t.getClass().getName(), results);
        }
    }

    public static void main(String[] arrrgh) {
        try {
            System.out.println("token - "+generateTimeToken());
            Thread.sleep(1000);
            System.out.println("token - "+generateTimeToken());
            Thread.sleep(1000);
            System.out.println("token - "+generateTimeToken());
            Thread.sleep(1000);
            System.out.println("token - "+generateTimeToken());
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(IdmUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
