package cz.vutbr.fit;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Login bean
 */
@SessionScoped
@Named("loginBean")
public class LoginBean implements Serializable
{
  private String name;
  private String password;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }
}
