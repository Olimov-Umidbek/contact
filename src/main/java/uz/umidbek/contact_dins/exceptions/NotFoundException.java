package uz.umidbek.contact_dins.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
