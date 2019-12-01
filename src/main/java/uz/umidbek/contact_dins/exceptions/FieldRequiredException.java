package uz.umidbek.contact_dins.exceptions;

public class FieldRequiredException extends RuntimeException {
    private int code;

    public FieldRequiredException(int code, String s) {
        super(s);
        this.code = code;
    }

    public FieldRequiredException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
