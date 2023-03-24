/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import pojo.Product;
import service.ProductService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import pojo.Category;

/**
 *
 * @author hp
 */
@ManagedBean
@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable {
    private int productId;
    private int productModalId;
    private String name;
    private BigDecimal price;
    private BigDecimal sll;
    private Part imgFile ;
    private Category category;
    private int count=1;
    public final static ProductService productService = new ProductService();
    
    public ProductBean() {
        
            String drinkid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("product_id");
                if (drinkid != null && !drinkid.isEmpty()) {
                Product d = productService.getProductById(Integer.parseInt(drinkid));
                this.productId = d.getId();
                this.name = d.getName();
                this.price = d.getPrice();
                this.sll = d.getSll();
                this.category = d.getCategory();
                this.count = 1;
            }
        
  
    }
    

    public List<Product> getProducts() {
        return productService.getProducts();
    }
    

    
    public Product getDrinkaaa() {
        return productService.getProductById(this.getProductId());
    }
    
    
    
    public String addProduct() {
        Product d;
      
        d = new Product();
        
        d.setName(getName());
        d.setPrice(getPrice());
        d.setSll(getSll());
        d.setCategory(getCategory());
        try {
            if (this.imgFile != null) {
                this.uploadFile();
                d.setImage("upload/" + this.imgFile.getSubmittedFileName());
            }
            
            if (productService.addOrSaveProduct(d) == true)
                return "product-list?faces-redirect=true";
        } catch (IOException ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "create-product";
    }
    
    public String updateProduct() {
        Product d = productService.getProductById(this.getProductId());
        d.setName(getName());
        d.setPrice(getPrice());
        d.setSll(getSll());
        d.setCategory(getCategory());
        try {
            if (this.imgFile != null) {
                this.uploadFile();
                d.setImage("upload/" + this.imgFile.getSubmittedFileName());
            }
            productService.addOrSaveProduct(d);
        } catch (IOException ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "product-list?faces-redirect=true";
    }
    

    private void uploadFile() throws IOException {
        String path = "C:/Users/Admin/Downloads/jsfsalesappv1/src/main/webapp/img/upload"
                + this.imgFile.getSubmittedFileName();
        
        try (InputStream in = this.imgFile.getInputStream();
             FileOutputStream out = new FileOutputStream(path)) {
            byte[] b = new byte[1024];
            int byteRead;
            while ((byteRead = in.read(b)) != -1) 
               out.write(b,0,byteRead); 
        }  
    }
    
    public String deleteProduct() {
        Product d = productService.getProductById(this.getProductId());
        productService.deleteProduct(d);
        return "product-list?faces-redirect=true";
    }
    public void minus(int product_id){
        this.count = this.count - 1;
    }
   
    public List<Product> getProductByCategoryId(int cate_id) {
        return productService.getProductsByCategoryId(cate_id);
    }
    //public List<Product> getTop6() {
    //    return productService.getTop6();
    //}
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the sll
     */
    public BigDecimal getSll() {
        return sll;
    }

    /**
     * @param sll the sll to set
     */
    public void setSll(BigDecimal sll) {
        this.sll = sll;
    }

    /**
     * @return the imgFile
     */
    public Part getImgFile() {
        return imgFile;
    }

    /**
     * @param imgFile the imgFile to set
     */
    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }

 
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }


   
    
}
