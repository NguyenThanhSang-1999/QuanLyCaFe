/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.transaction.Transactional;
import pojo.Category;
import pojo.Product;
import service.CategoryService;

/**
 *
 * @author hp
 */
@ManagedBean
@Named(value = "cateBean")
@SessionScoped
public class CateBean implements Serializable{

    
    public final static CategoryService categoryService = new CategoryService();
    /**
     * Creates a new instance of CateBean
     */
    public CateBean() {
    }
    
    public List<Category> getCategorys() {
        return categoryService.getCategorys();
    }
    
//    @Transactional
//    public Set<Product> getProductByCategoryId(int cateId) {
//        return categoryService.getProductByCategoryById(cateId);
//    }
}
