package pl.camp.it.exception;

import java.io.IOException;

public class NegativeSexCharException extends IOException {
    String sex;

    public NegativeSexCharException(String sex) {
        this.sex = sex;
    }
}
