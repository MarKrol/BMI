package pl.camp.it.exception;

public class NegativeDoubleValueException extends Exception{
    String value;

    public NegativeDoubleValueException(String value) {
        this.value = value;
    }
}
