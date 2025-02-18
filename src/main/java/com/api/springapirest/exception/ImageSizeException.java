package com.api.springapirest.exception;

public class ImageSizeException extends ImageException {
    public ImageSizeException() {
        super("La taille de l'image dépasse la limite de 5 Mo.");
    }
}
