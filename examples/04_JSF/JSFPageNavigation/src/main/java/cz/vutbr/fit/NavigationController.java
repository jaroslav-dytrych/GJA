/*
 * Adapted from http://www.tutorialspoint.com/jsf/jsf_page_navigation.htm
 * Updated to Jakarta 10
 */
package cz.vutbr.fit;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * NavigationController class is a RequestScoped bean, which returns names of
 * xhtml files. CommandLinks and CommandButtons uses this bean to navigate
 * between pages
 */
@RequestScoped
@Named("navigationController")
public class NavigationController {

    private static final long serialVersionUID = 1L;
    @Inject
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;

    /**
     *
     * @return String "page1"
     */
    public String moveToPage1() {
        // JSF will guess the page by returned value "page1" and will find page1.xhtml
        return "page1";
    }

    /**
     *
     * @return String "page2"
     */
    public String moveToPage2() {
        // JSF will guess the page by returned value "page2" and will find page2.xhtml
        return "page2";
    }

    /**
     *
     * @return String "home"
     */
    public String moveToHomePage() {
        // JSF will guess the page by returned value "home" and will find home.xhtml
        return "home";
    }

    /**
     *
     * @return one of page file names, depending on the value of pageId
     */
    public String showPage() {

        // JSF will guess the page by returned value conditioned by String pageId
        if (pageId == null) {
            return "home";
        }
        System.out.println(pageId);
        return switch (pageId) {
            case "1" ->
                "page1";
            case "2" ->
                "page2";
            case "3" ->
                "home";
            case "1234" ->
                "page3000";
            default ->
                "home";
        };
    }

    /**
     * see faces-config.xml
     *
     * @return String "page", which will result into redirecting to page1.xhtml
     * according to navigation rule in faces-config.xml
     */
    public String processPage1() {
        return "page";
    }

    /**
     *
     * see faces-config.xml
     *
     * @return String "page", which will result into redirecting to page2.xhtml
     * according to navigation rule in faces-config.xml
     */
    public String processPage2() {
        return "page";
    }

    /**
     *
     * @return pageId managed property
     */
    public String getPageId() {
        return pageId;
    }

    /**
     *
     * @param pageId managed property
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
