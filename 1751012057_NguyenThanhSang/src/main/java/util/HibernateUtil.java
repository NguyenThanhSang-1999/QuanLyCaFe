/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
// */
package util;
import pojo.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Environment;
import pojo.Category;
import pojo.Product;
import pojo.ProductType;
/**
 *
 * @author hp
 */
public class HibernateUtil {
    private final static SessionFactory Factory;
    
    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/webcf_db");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "123456");
        conf.setProperties(props);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(ProductType.class);
        conf.addAnnotatedClass(Category.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        Factory = conf.buildSessionFactory(registry);
    }
    
    public static SessionFactory getSessionFactory() {
        return Factory;
    }
}
