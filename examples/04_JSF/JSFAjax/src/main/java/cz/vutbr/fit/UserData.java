package cz.vutbr.fit;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * SessionScoped UserData bean, which holds name of a user. The value is changed
 * via Ajax HTTP call in home.xhtml.
 */
@Named("userData")
@SessionScoped
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    /**
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return a welcome message containing user's name
     */
    public String getWelcomeMessage() {
        return "Hello " + name;
    }
}
