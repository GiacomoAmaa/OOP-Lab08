package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 */
public class ControllerImpl implements Controller {
    private List<String> printed = new LinkedList<>();
    private String next;
    /**
     * @param string 
     *              string to set as next to print
     * @throws IllegalArgumentException
     */
    public void setNextToPrint(final String string) {
        if (string.isBlank()) {
            throw new IllegalArgumentException("");
        }
        this.next = string;
    }
    /**
     * @return next string to print
     */
    public String getNextToPrint() {
        return this.next;
    }
    /**
     * @return the list of printed strings
     */
    public List<String> getPrinted() {
       return this.printed;
    }
    /**
     * @throws IllegalStateException
     */
    public void printNext() {
        if (this.next.isBlank()) {
            throw new IllegalStateException("There is no string set");
        }
        printed.add(this.next);
        System.out.println(this.next);
    }

}
