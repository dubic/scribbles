/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.ui;

import com.dubic.scribbles.idm.models.User;
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
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.jsf.FacesContextUtils;

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
    private User user;
    private String profileName;

    //<editor-fold defaultstate="collapsed" desc="getter setters">
    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//</editor-fold>
    /**
     * Creates a new instance of ProfileRequest
     */
    public ProfileRequest() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        String name = request.getParameter("name");

        log.debug("pname = " + name);
        if (name == null) {
            try {
                response.sendRedirect("home.jsf");
//                FacesContext.getCurrentInstance().responseComplete();
            } catch (IOException ex) {
                log.fatal(ex.getMessage());
            }
        } else {
            //load profile by screen name
            identityService = (IdentityService) FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getBean("identityService");
            this.user = identityService.findUserByScreenName(name);
        }

    }

    @PostConstruct
    public void loadUser() {
        log.debug("profile name passed = " + profileName);
    }

    public void uploadPic() throws IOException {
        log.debug("uploadPic...");
        
        // Copy uploaded file to destination path
        InputStream inputStream = part.getInputStream();
        String pixName = IdmCrypt.encodeMD5(this.user.getProfile().getId() + "", "pix");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\temp\\" + pixName);

        BufferedImage image = ImageIO.read(inputStream);
        Image scaledImage = image.getScaledInstance(180, 200, Image.SCALE_DEFAULT);
        ImageIO.write(toBufferedImage(scaledImage), "jpg", fileOutputStream);
        IOUtils.closeQuietly(fileOutputStream);
        this.user.getProfile().setPicture(pixName);
        profileService.updateProfile(this.user.getProfile());

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
