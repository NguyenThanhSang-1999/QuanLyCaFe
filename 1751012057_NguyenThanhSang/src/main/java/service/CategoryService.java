/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import util.HibernateUtil;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Category;
import pojo.Product;

/**
 *
 * @author hp
 */
public class CategoryService {
    
    private final static SessionFactory Factory = HibernateUtil.getSessionFactory();
    
    
    public List<Category> getCategorys() {
        try (Session session = Factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> query = builder.createQuery(Category.class);
            
            Root<Category> root = query.from(Category.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    
    
     public Category getCategoryById(int cateId) {
         try (Session session = Factory.openSession()) {
           Category cate = (Category) session.get(Category.class, cateId);
           return cate;
        }
    }
     
     
//    @Transactional
//    public Set<Product> getProductByCategoryById(int cateId){
//         try (Session session = Factory.openSession()) {
//           Category cate = (Category) session.get(Category.class, cateId);
//           return cate.getProducts();
//        }
//    }
}
