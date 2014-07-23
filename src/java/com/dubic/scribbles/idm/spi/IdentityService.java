/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.spi;

import com.dubic.scribbles.dto.UserData;
import com.dubic.scribbles.idm.models.Group;
import com.dubic.scribbles.idm.models.Role;
import com.dubic.scribbles.idm.models.Token;
import com.dubic.scribbles.idm.models.User;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

/**
 *
 * @author dubem
 */
public interface IdentityService {

    public User userRegistration(UserData userData) throws PersistenceException;

    public String getUniqueEmail(String email) throws PersistenceException;

    public String validateScreenName(String screenName) throws PersistenceException;

    public void updateUser(User user) throws PersistenceException;

    /**the implementation should
     * <ul><li>GENERATE RANDOM TOKEN USING TIME MILLIS</li>
     *<li>CREATE AND SAVE TOKEN IN DB</li>
     *<li>SEND MAIL TO USER</li>
     * @param email
     */
    public void getChangePwdToken(String email) throws PersistenceException;

    /**resets the user password
     * <ul><li>GETS USER FROM TOKEN</li>
     *<li>UPDATE USER PASSWORD IN DB</li>
     *<li>UPDATE TOKEN IN DB</li>
     * @param token
     * @param pwd
     * @throws com.dubic.scribbles.idm.spi.LinkExpiredException if token has expired
     */
    public void changePassword(String token, String pwd) throws InvalidTokenException,LinkExpiredException;

    public User findUserByEmailandPasword(String email, String pwd);
    
    public User findUserByEmail(String email);

    public Role createRole(String name, String desc) throws PersistenceException;

    public Group createGroup(Group grp) throws PersistenceException;

    public Group createGroup(String name, String desc, User creator, Long parent) throws PersistenceException;

    public Token createActivationToken(User user);

    public void activateUser(String ua) throws LinkExpiredException,Exception;

    public User getUserLoggedIn();

    public void assignRole(Long roleId, Long userId) throws EntityNotFoundException,PersistenceException;

    public void addUserToGroup(Long userId, Long grpId) throws EntityNotFoundException,PersistenceException;
}
