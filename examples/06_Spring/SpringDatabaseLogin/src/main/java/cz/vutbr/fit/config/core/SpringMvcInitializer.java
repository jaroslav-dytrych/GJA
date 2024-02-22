/*
 * Adapted from http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
 */

package cz.vutbr.fit.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cz.vutbr.fit.config.AppConfig;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{AppConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return null;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

}
