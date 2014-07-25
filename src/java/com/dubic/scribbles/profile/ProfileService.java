/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.profile;

import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.models.Profile;
import javax.persistence.PersistenceException;

/**
 *
 * @author dubem
 */
public interface ProfileService {
    
    /**loads a profile of a user using a supplied user id
     *
     * @param userId
     * @return database profile
     */
    public Profile loadProfile(Long userId);
    
    /**loads a profile of a user using a supplied user
     *
     * @param user
     * @return database profile
     */
    public Profile loadProfile(User user);
    
    
    
    /**updates a users profile in the db and returns it
     *
     * @param profile
     * @return
     */
    public Profile updateProfile(Profile profile) throws PersistenceException;
}
