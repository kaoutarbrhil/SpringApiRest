package com.api.springapirest.exception;

public class ImageEmptyException extends ImageException {
    public ImageEmptyException() {
        super("L'image ne peut pas être vide.");
    }
}
