/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.db;


import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**Generic database for persistence
 *
 * @author DUBIC
 * @since dubic-database 0.0.1
 */
@Named
public class Database {

    private static final Logger log = Logger.getLogger(Database.class);
    @PersistenceContext
    private EntityManager em;

    public Database() {
    }

    @Transactional
    public <T> void persist(T...entities) throws PersistenceException {
        for (T t : entities) {
            em.persist(t);
            log.debug(String.format("persisted [%s]", t));
        }
    }

    @Transactional
    public <T> void merge(T...entities) throws PersistenceException {
        for (T t : entities) {
            em.merge(t);
            log.debug(String.format("merged [%s]", t));
        }
    }

    @Transactional
    public <T> void delete(T t) throws PersistenceException {
        log.debug("delete {}");
        em.remove(t);
        log.debug("delete completed...");
    }

    
    /**returns an entity using em.find.does not throw exception if no entity is found
     *
     * @param <T>
     * @param tClass the class of the entity
     * @param t the id of the entity which is the primary key
     * @return found entity by id or null
     * @since dubic-database 0.0.1
     */
    public <T> T get(Class<T> tClass, Object t) {
        log.debug("get {}");
        return em.find(tClass, t);
    }
    
    /**returns an entity using em.find and throws exception if no entity is found
     *
     * @param <T>
     * @param tClass tClass the class of the entity
     * @param t the id of the entity which is the primary key
     * @return found entity by id
     * @throws EntityNotFoundException if no entity is found
     * @since dubic-database 0.0.2
     */
    public <T> T find(Class<T> tClass, Object t) throws EntityNotFoundException {
        log.debug("find {}");
        T find = em.find(tClass, t);
        if(find == null){
            throw new EntityNotFoundException("No "+tClass.getSimpleName()+" found by id - "+t);
        }
        return find;
    }
    
    public <T> T getReference(Class<T> tClass, Object t) throws EntityNotFoundException {
        log.debug("getReference {}");
        T find = em.getReference(tClass, t);
        if(find == null){
            throw new EntityNotFoundException("No "+tClass.getSimpleName()+" found by id - "+t);
        }
        return find;
    }
   
    
    public <T> List<T> getAll(Class<T> tClass, int page, int size) {
        log.debug("getAll {}");
        int start = (page -1)*size;
        log.debug("\nstart = " + start + "\nsize = " + size);
        return em.createQuery("SELECT t FROM " + tClass.getSimpleName() + " t", tClass)
                .setFirstResult(start).setMaxResults(size).getResultList();
    }

    
    public <T> List<T> getAll(Class<T> tClass) {
        log.debug("getAll {}");
        return em.createQuery("SELECT t FROM " + tClass.getSimpleName() + " t", tClass)
                .getResultList();
    }
    
    /**creates a named query from the query string and type class.abstracts entity manager from other classes 
     * usage
     * @param <T>
     * @param namedQuery
     * @param tClass
     * @return a created typed named query
     * @since dubic-database 0.0.2
     */
    public <T> TypedQuery<T> namedQuery(String namedQuery, Class<T> tClass){
        return em.createNamedQuery(namedQuery, tClass);
    }
    
    /**creates a simple query from the query string and type class.abstracts entity manager from other classes 
     *
     * @param <T>
     * @param query the query string
     * @param tClass
     * @return  a created typed named query
     * @since dubic-database 0.0.2
     */
    public <T> TypedQuery<T> createQuery(String query, Class<T> tClass){
        return em.createQuery(query, tClass);
    }
    
    /**creates a native query from the query string and type class.abstracts entity manager from other classes 
     *
     * @param <T>
     * @param nativeQuery the native query
     * @param tClass
     * @return  a created typed named query
     * @since dubic-database 0.0.2
     */
    public <T> Query createNativeQuery(String nativeQuery, Class<T> tClass){
        return em.createNativeQuery(nativeQuery, tClass);
    }

    /**creates a query from query string passed
     *
     * @param query
     * @return a created query
     * @since dubic-database 0.0.2
     */
    public Query createQuery(String query){
        return em.createQuery(query);
    }
    
    /**creates a query from query string passed.query is paginated with the page and step
     *
     * @param query
     * @param page
     * @param size
     * @return a created query
     * @since dubic-database 0.0.2
     */
    public Query createQuery(String query,int page, int size){
        log.debug("createQuery {}");
        int start = (page -1)*size;
        log.debug("\nstart = " + start + "\nsize = " + size);
        return em.createQuery(query).setFirstResult(start).setMaxResults(size);
    }
    
    /**persists a batch of entities in the same transaction
     *
     * @param <T>
     * @param t the entity array
     * @throws DBException
     * @since dubic-database 0.0.2
     */
    @Transactional
    public <T> void persistBatch(List<T> t) throws PersistenceException {
        log.debug("persist batch {} - "+t.size());
        for (int i = 0; i < t.size(); i++) {
            log.debug("saving...."+i);
            em.persist(t.get(i));
        }
        log.debug("persist batch completed...");
    }
   
}
