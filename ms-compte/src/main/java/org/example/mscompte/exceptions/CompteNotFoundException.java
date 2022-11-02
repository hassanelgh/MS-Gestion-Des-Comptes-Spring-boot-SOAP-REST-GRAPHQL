package org.example.mscompte.exceptions;

public class CompteNotFoundException extends RuntimeException {

    public CompteNotFoundException(String m) {
        super(m);
    }
}
