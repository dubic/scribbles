/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.ui;

import com.dubic.scribbles.idm.spi.IdentityService;
import com.dubic.scribbles.models.Profile;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

/**
 *
 * @author dubem
 */
@ManagedBean(name = "profile")
@RequestScoped
public class ProfileRequest {

    private final Logger log = Logger.getLogger(getClass());

    private Part part;
    @ManagedProperty("#{identityService}")
    private IdentityService identityService;
    private Profile profile;

    //<editor-fold defaultstate="collapsed" desc="getter setters">
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

//</editor-fold>
    /**
     * Creates a new instance of ProfileRequest
     */
    public ProfileRequest() {
    }

    @PostConstruct
    public void loadUser() {
        this.profile = identityService.getUserLoggedIn().getProfile();
    }

    public void uploadPic() throws IOException {
        log.debug("uploadPic...");
        log.debug("name - " + part.getName());
        log.debug("header filename - " + getFileName(part));
        log.debug("size - " + part.getSize());
        // Copy uploaded file to destination path
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = part.getInputStream();
            outputStream = new FileOutputStream("C:\\temp\\" + getFileName(part));

            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    private String getFileName(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        System.out.println("***** partHeader: " + partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}
