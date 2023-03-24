/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import util.HibernateUtil;
import java.util.List;
import pojo.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author hp
 */
public class UserService {
    
    private final static SessionFactory Factory = HibernateUtil.getSessionFactory();
    
    
    public boolean addUser (User u) {
        try (Session session = Factory.openSession()) {
            try {
                session.getTransaction().begin();
           
                u.setPassword(DigestUtils.md5Hex(u.getPassword()));
                session.save(u);
                session.getTransaction().commit();
                return true;
            } catch (Exception  ex) {
                 session.getTransaction().rollback();
                 return false;
            }
        } catch (Exception ex) {
            return false;
        }    
    }
    
    public User login (String username, String password) {
        password = DigestUtils.md5Hex(password);
        try (Session session = Factory.openSession()) {
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<User> q = b.createQuery(User.class);
            
            Root<User> root = q.from(User.class);
            q.select(root);
 
            q.where(b.and(
                    b.equal(root.get("username").as(String.class),username),
                    b.equal(root.get("password").as(String.class),password)
            ));
            
            return session.createQuery(q).getSingleResult();
            
        }
        
    }
    
    
    public List<User> getUsers() {
        try (Session session = Factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            
            Root<User> root = query.from(User.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
}
