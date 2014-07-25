/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.profile;

import com.dubic.scribbles.db.Database;
import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.models.Profile;
import com.dubic.scribbles.utils.IdmUtils;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import org.apache.log4j.Logger;

/**
 *
 * @author dubem
 */
@Named(value = "profileService")
public class ProfileServiceBean implements ProfileService {

    private final Logger log = Logger.getLogger(getClass());
    @Inject private Database db;
//    private String pixpath;

    @Override
    public Profile loadProfile(Long userId) {
        log.debug("loadProfile - " + userId);
        List<Profile> resultList = db.createQuery("SELECT p FROM Profile p WHERE P.user.id = :uid", Profile.class).setParameter("uid", userId)
                .getResultList();
        return IdmUtils.getFirstOrNull(resultList);
    }

    @Override
    public Profile loadProfile(User user) {
        return loadProfile(user.getId());
    }

    @Override
    public Profile updateProfile(Profile profile) throws PersistenceException {
        log.debug("updateProfile - " + profile.getId());
        profile.setUpdated(new Date());
        db.merge(profile);
        return profile;
    }

}
