package cz.vutbr.fit;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * SessionScoped UserData bean, which holds "login data" for the user. country.
 * The data are manipulated by custom component, which is a registration form.
 */
@Named("user")
@SessionScoped
public class UserBean implements Serializable {

    private String name;
    private String email;

    /**
     * Gets user's name
     * 
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets user's name
     *
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets user's email
     *
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user's email
     *
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns outcome for page navigation
     *
     * @return "result", used for page navigation to result.xhtml
     */
    public String registerAction() {
        return "result";
    }
}
