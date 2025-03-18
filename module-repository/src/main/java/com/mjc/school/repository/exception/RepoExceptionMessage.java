package com.mjc.school.repository.exception;

public enum RepoExceptionMessage {
    //
    FAIL_TO_LOAD_RESOURCE(Constants.ERROR_000007, "Failed to load resource file: %s"),
    RESOURCE_FILE_NOT_FOUND(Constants.ERROR_000008, "Resource file not found : %s");

    private final String errorMessage;

    private RepoExceptionMessage(String errorCode, String message) {
        //
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getErrorMessage() {
        //
        return this.errorMessage;
    }

    private static class Constants {
        //
        private static final String ERROR_000007 = "000007";
        private static final String ERROR_000008 = "000008";
    }
}
