/**
 * Adapted from http://www.tutorialspoint.com/spring/spring_java_based_configuration.htm
 */

package cz.vutbr.fit.gja.springConfiguration;

import org.springframework.context.annotation.*;

@Configuration
public class HelloWorldConfig {
  
  // one bean can also construct another in @Configuration
  // to import bean definition from another @Configuration - @Import(Bean.class)

  @Bean
  public HelloWorld helloWorld() {
    return new HelloWorld();
  }
}
