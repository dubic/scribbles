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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dubic
 * @since idm 1.0.0
 */
@Entity
@Table(name = "idm_role")
@NamedQueries({
    @NamedQuery(name = "role.Find.name", query = "SELECT r FROM Role r WHERE r.name = :name")})
public class Role implements Serializable {

    private Long id;
    private String name;
    private String description = "";
    private Date createDate = new Date();

    public Role() {
    }

    public Role(String name, String desc) {
        this.name = name.trim();
        this.description = desc;
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

    @Column(name = "name", unique = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "create_dt")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
