package com.api.springapirest.exception;

public class ImageFormatException extends ImageException {
    public ImageFormatException() {
        super("Le type d'image est invalide. Formats autorisés : .png, .jpg, .svg.");
    }
}
