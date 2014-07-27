/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.models;

import com.dubic.scribbles.dto.UserData;
import com.dubic.scribbles.models.Profile;
import com.dubic.scribbles.utils.IdmUtils;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * holds user details in database.uses email as username but user can create
 * screen name
 *
 * @author dubic
 * @since idm 1.0.0
 */
@Entity
@Table(name = "idm_user")
@NamedQueries({
    @NamedQuery(name = "user.find.mail.password", query = "SELECT u FROM User u WHERE u.password = :pwd and u.email = :email"),
    @NamedQuery(name = "user.findmail.email", query = "SELECT u.email FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "user.find.screenName", query = "SELECT u FROM User u WHERE u.screenName = :screenName"),
    @NamedQuery(name = "user.findscreename.scname", query = "SELECT u.screenName FROM User u WHERE u.screenName = :scname"),
    @NamedQuery(name = "user.find.id.mail", query = "SELECT u FROM User u WHERE u.email = :email and u.id = :id"),
    @NamedQuery(name = "user.find.mail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements UserDetails, Serializable {

    private Long id;
    private String screenName;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private Date lastLoginDate;
    private boolean locked;
    private Date lockDate;
    private boolean activated;
    private Date passwordChangeDate;
    private boolean passwordExpired;
    private String phone;
    private String secretQuestion;
    private String secretAnswer;
    private Date createDate = new Date();
    private Date modifiedDate = new Date();
    private Profile profile;
    private List<Role> roles = new ArrayList<Role>();
    private List<Group> groups = new ArrayList<Group>();

    public User() {
    }

    public User(UserData userData) {
        this.email = userData.getEmail();
        this.screenName = userData.getScreenName();
        this.password = userData.getPassword();
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "Empty screen name")
    @Column(unique = true, length = 20, name = "screen_name")
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @NotEmpty(message = "Email not set")
    @Email(message = "Email not valid")
    @Column(unique = true, name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "firstname", length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column( name = "lastname", length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "last_login_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(name = "locked")
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Column(name = "lock_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    @Column(name = "active")
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Column(name = "pwd_change_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(Date passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    @Column(name = "pwd_expired")
    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    @Column(unique = true, name = "phone", length = 25)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany
    @JoinTable(name = "idm_user_roles", joinColumns = {
        @JoinColumn()})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    @OneToMany
    @JoinTable(name = "idm_user_groups", joinColumns = {
        @JoinColumn()})
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Column(name = "create_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modified_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @NotNull(message = "User must have a profile")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @NotEmpty(message = "User must have a password")
    @Column(name = "password")
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static void main(String[] agggh) {
        User u = new User();
        u.setScreenName("dubic");
        u.setEmail("ddd@mmm.com");
        u.setProfile(new Profile());
        try {
            IdmUtils.validate(u);
        } catch (ConstraintViolationException ex) {
            for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
                System.out.println("msg - " + v.getMessage());
            }
        }
    }
}
