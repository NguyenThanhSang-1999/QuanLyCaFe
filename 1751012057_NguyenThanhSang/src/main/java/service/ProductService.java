/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import util.HibernateUtil;
import java.util.List;
import java.util.Set;
import pojo.Product;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author hp
 */
public class ProductService {
    
    private final static SessionFactory Factory = HibernateUtil.getSessionFactory();
    
    
    public List<Product> getProducts() {
        try (Session session = Factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);
            
            Root<Product> root = query.from(Product.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    
    public boolean addOrSaveProduct(Product d) {
         try (Session session = Factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(d);
                session.getTransaction().commit();
            } catch (Exception ex) {
                 session.getTransaction().rollback();
                 return false;
            }
        }
         return true;
    }
    
    public boolean updateProduct(Product d) {
        try (Session session = Factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.update(d);
                session.getTransaction().commit();
            } catch (Exception ex) {
                 session.getTransaction().rollback();
                 return false;
            }
        }
         return true;
    }
    
   public boolean deleteProduct(Product d) {
        try (Session session = Factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(d);
                session.getTransaction().commit();
            } catch (Exception ex) {
                
            }
        }
        return true;
    }
   
    
    public Product getProductById(int productId) {
         try (Session session = Factory.openSession()) {
           Product drink = (Product) session.get(Product.class, productId);
           return drink;
        }
    }

    
    public List<Product> getProductsByCategoryId(int category_id) {
        try (Session session = Factory.openSession()) {
          Query query = session.createQuery("FROM Product WHERE category_id = :category_id");
          query.setParameter("category_id", category_id);
          List<Product> list = query.list();
          return list;
        }
    }

    public List<Product> getProducts(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
