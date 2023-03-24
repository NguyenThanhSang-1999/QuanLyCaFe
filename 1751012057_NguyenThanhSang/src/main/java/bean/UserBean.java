/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import pojo.User;
import service.UserService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

/**
 *
 * @author hp
 */
@ManagedBean
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    private String email;
    private String username;
    private String password;
    @Transient
    private String confirmPassword;
    
    public final static UserService userService = new UserService();
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    public String register() {
        if (!this.password.isEmpty() && this.getPassword().equals(this.getConfirmPassword())) {
            User u = new User();
            u.setUsername(getUsername());
            u.setEmail(getEmail());
            u.setPassword(getPassword());
            if (userService.addUser(u) == true) {
                return "login?faces-redirect=true";
            }
        }
        return "register?faces-redirect=true";
    }
    
    public String login() {
        User u = userService.login(username, password);
        if (u != null) {
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
            return "index?faces-redirect=true";
        }
        return "login?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        return "index?faces-redirect=true";
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
