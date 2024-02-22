/*
 * Adapted from http://www.tutorialspoint.com/spring/spring_mvc_form_handling_example.htm
 */

package cz.vutbr.fit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

@Controller
public class StudentController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView student() {
    return new ModelAndView("student", "command", new Student());
  }

  @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
  public String addStudent(@ModelAttribute("SpringMVC") Student student, ModelMap model) {
    model.addAttribute("name", student.getName());
    model.addAttribute("age", student.getAge());
    model.addAttribute("id", student.getId());
    return "result";
  }
}
