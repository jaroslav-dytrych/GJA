/*
 * Adapted from http://javahash.com/spring-security-hello-world-example/
 */

package cz.vutbr.fit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

  @RequestMapping(value = {"/", "/helloworld**"}, method = RequestMethod.GET)
  public ModelAndView welcomePage() {

    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security 3.2.3 Hello World Application");
    model.addObject("message", "Welcome Page !");
    model.setViewName("helloworld");
    return model;

  }

  @RequestMapping(value = "/protected**", method = RequestMethod.GET)
  public ModelAndView protectedPage() {

    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security 3.2.3 Hello World");
    model.addObject("message", "This is protected page - Only for Administrators !");
    model.setViewName("protected");
    return model;

  }

  @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
  public ModelAndView superAdminPage() {

    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security 3.2.3 Hello World");
    model.addObject("message", "This is confidential page - Need Super Admin Role !");
    model.setViewName("protected");

    return model;

  }

  @RequestMapping(value = "/rprotected", method = RequestMethod.GET)
  public String redirectProtected() {
    return "redirect:protected";
  }
  @RequestMapping(value = "/rconfidential", method = RequestMethod.GET)
  public String redirectConfidential() {
    return "redirect:confidential";
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:/helloworld";
  }
}
