/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.ui;

import com.dubic.scribbles.idm.spi.IdentityService;
import com.dubic.scribbles.models.Profile;
import com.dubic.scribbles.profile.ProfileService;
import com.dubic.scribbles.utils.IdmCrypt;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
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
    @ManagedProperty("#{profileService}")
    private ProfileService profileService;
    private Profile profile;

    //<editor-fold defaultstate="collapsed" desc="getter setters">
    
    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
    
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
        InputStream inputStream = part.getInputStream();
        String pixName = IdmCrypt.encodeMD5(profile.getId()+"", "pix");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\temp\\" + pixName);
        

        BufferedImage image = ImageIO.read(inputStream);
        Image scaledImage = image.getScaledInstance(180, 200, Image.SCALE_DEFAULT);
        ImageIO.write(toBufferedImage(scaledImage), "jpg", fileOutputStream);
        IOUtils.closeQuietly(fileOutputStream);
        this.profile.setPicture(pixName);
        profileService.updateProfile(profile);
                
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
    
    private BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }
}
