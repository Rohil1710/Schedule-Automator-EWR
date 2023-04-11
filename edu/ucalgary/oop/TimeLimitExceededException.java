/**
@author Tanis Smith <a href="mailto:tanis.smith@ucalgary.ca">
tanis.smith@ucalgary.ca</a>
@author Rohil Dhillon <a href="mailto:Rohil.Dhillon@ucalgary.ca">
Rohil.Dhillon@ucalgary.ca</a>
@author Mehrnaz Zafari <a href="mailto:mehrnaz.zafari@ucalgary.ca">
mehrnaz.zafari@ucalgary.ca</a>
@author Abhyudai Singh <a href="mailto:abhyudai.singh@ucalgary.ca">
abhyudai.singh@ucalgary.ca</a>
@version 1.2
@since 1.0
*/
package edu.ucalgary.oop;

public class TimeLimitExceededException extends Exception {

    /**
     * Constructs a new TimeLimitExceededException object with the specified error
     * message.
     *
     * @param error the error message associated with the exception
     */
    public TimeLimitExceededException(String error) {
        super(error);
    }
}
