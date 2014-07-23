/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dubic
 * @since idm 1.0.0
 */
@Entity
@Table(name = "idm_group")
@NamedQueries({
    @NamedQuery(name = "group.Find.name", query = "SELECT g FROM Group g WHERE g.name = :name")})
public class Group implements Serializable {

    private Long id;
    private String name;
    private User creator;
    private boolean active = true;
    private String description;
    private Long parent;
    private Date createDate = new Date();
    private Date modifiedDate = new Date();

    public Group() {
    }

    public Group(String name,String description,User creator,Long parent) {
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.parent = parent;
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

    @Column(name = "name", unique = true,length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JoinColumn(name = "creator_id")
    @OneToOne(fetch = FetchType.LAZY)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "parent_id")
    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Column(name = "create_dt")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modified_dt")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
