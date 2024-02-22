/*
 * Adapted from http://www.tutorialspoint.com//spring/spring_bean_post_processors.htm
 */

package cz.vutbr.fit.gja.beanPostProcessor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.BeansException;

public class InitHelloWorld implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("BeforeInitialization : " + beanName);
    return bean; // you can return any other object as well
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("AfterInitialization : " + beanName);
    return bean; // you can return any other object as well
  }
}
