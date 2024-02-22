/*
 * Project: Examples for GJA course
 * Author: Ing. Jaroslav Dytrych idytrych@fit.vutbr.cz
 * Refactored: Bc. Vít Barták xbarta47@stud.fit.vutbr.cz
 * File: Entry.java
 * Description: Calendar entry
 */
/**
 * @file Entry.java
 *
 * Calendar entry
 */
package cz.vutbr.fit.knot.gja.calendar;

/**
 * Calendar entry
 */
public class Entry {

    /**
     * Hour
     */
    private String hour;
    /**
     * Entry description
     */
    private String description;
    /**
     * Color
     */
    private String color;

    /**
     * Constructor
     *
     * @param hour Hour to which entry should be placed
     */
    public Entry(String hour) {
        this.hour = hour;
        this.description = "";
    }

    /**
     * Get hour
     *
     * @return Hour
     */
    public String getHour() {
        return this.hour;
    }

    /**
     * Get color
     *
     * @return Color
     */
    public String getColor() {
        if (description.equals("")) {
            return "lightblue";
        } else {
            return "red";
        }
    }

    /**
     * Get description
     *
     * @return Description
     */
    public String getDescription() {
        if (description.equals("")) {
            return "None";
        } else {
            return this.description;
        }
    }

    /**
     * Set description
     *
     * @param descr New description
     */
    public void setDescription(String descr) {
        description = descr;
    }
}  // public class Entry
