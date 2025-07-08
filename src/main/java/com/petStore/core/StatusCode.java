package com.petStore.core;

public enum StatusCode {

    SUCCESS(200, "The request succeeded"),
    CREATED(201, "A new resource was created"),
    BAD_REQUEST(400, "Missing required field: name"),
    UNAUTHORIZED(401, "Invalid access token"),
    NOT_FOUND(404, "Cannot Find Requested Resource"),
    NO_CONTENT(204, "No content to send in the response body");

    private int code;

    public String getStatusMessage() {
        return statusMessage;
    }

    public int getCode() {
        return code;
    }

    private String statusMessage;


   StatusCode(int code, String statusMessage) {
        this.code = code;
        this.statusMessage = statusMessage;
    }
}
