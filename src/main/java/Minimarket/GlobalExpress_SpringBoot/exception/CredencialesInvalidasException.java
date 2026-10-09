package Minimarket.GlobalExpress_SpringBoot.exception;

public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("Usuario o contraseña incorrectos.");
    }
}
