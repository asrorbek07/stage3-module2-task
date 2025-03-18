package com.mjc.school.service.exception;

public enum ServiceExceptionMessage {
    NEWS_ID_DOES_NOT_EXIST(Constants.ERROR_000001, "NewsModel with id %d does not exist."),
    AUTHOR_ID_DOES_NOT_EXIST(Constants.ERROR_000002, "AuthorModel Id does not exist. AuthorModel Id is: %s"),
    VALIDATE_NEGATIVE_OR_NULL_NUMBER(Constants.ERROR_000003, "%s can not be null or less than 1. %s is: %s"),
    VALIDATE_NULL_STRING(Constants.ERROR_000004, "%s can not be null. %s is null"),
    VALIDATE_STRING_LENGTH(Constants.ERROR_000005, "%s can not be less than %d and more than %d symbols. %s is %s"),
    VALIDATE_INT_VALUE(Constants.ERROR_000006, "%s should be number");

    private final String errorMessage;

    private ServiceExceptionMessage(String errorCode, String message) {
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    private static class Constants {
        private static final String ERROR_000001 = "000001";
        private static final String ERROR_000002 = "000002";
        private static final String ERROR_000003 = "000003";
        private static final String ERROR_000004 = "000004";
        private static final String ERROR_000005 = "000005";
        private static final String ERROR_000006 = "000006";
    }
}
