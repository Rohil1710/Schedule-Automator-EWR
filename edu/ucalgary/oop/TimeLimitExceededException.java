package edu.ucalgary.oop;

public class TimeLimitExceededException extends Exception {

    public TimeLimitExceededException(String error) {
        super(error);
    }
}
