package pl.camp.it.exception;

public class NegativeIntegerValueException extends Exception {
    private String age;

    public NegativeIntegerValueException(String age){
        this.age = age;
    }
}
