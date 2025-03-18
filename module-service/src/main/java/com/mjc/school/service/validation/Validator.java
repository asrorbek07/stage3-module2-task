package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.NotValidException;
import com.mjc.school.service.exception.ServiceExceptionMessage;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    //
    private static final String NEWS_ID = "NewsModel id";
    private static final String NEWS_CONTENT = "NewsModel content";
    private static final String AUTHOR_ID = "AuthorModel id";
    private static final String NEWS_TITLE = "NewsModel title";
    private static final String AUTHOR_NAME = "AuthorModel name";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 3;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;

    private static boolean isEmpty(String str) {
        //
        return str == null || str.isEmpty();
    }

    public void validateAuthor(AuthorDtoRequest authorDtoRequest) {
        //
        this.validateString(authorDtoRequest.getName(), AUTHOR_NAME, AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);
        if (authorDtoRequest.getId() != null) {
            this.validateNumber(authorDtoRequest.getId(), AUTHOR_ID);
        }
    }

    public void validateNews(NewsDtoRequest newsDtoRequest) {
        //
        this.validateString(newsDtoRequest.getTitle(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        this.validateString(newsDtoRequest.getContent(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        this.validateNumber(newsDtoRequest.getAuthorId(), AUTHOR_ID);
        if (newsDtoRequest.getId() != null) {
            this.validateNumber(newsDtoRequest.getId(), NEWS_ID);
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        //
        if (isEmpty(value)) {
            throw new NotValidException(String.format(ServiceExceptionMessage.VALIDATE_NULL_STRING.getErrorMessage(), parameter, parameter));
        } else if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new NotValidException(String.format(ServiceExceptionMessage.VALIDATE_STRING_LENGTH.getErrorMessage(), parameter, minLength, maxLength, parameter, value));
        }
    }

    private void validateNumber(Long id, String parameter) {
        //
        if (id == null || id < 1L) {
            throw new NotValidException(String.format(ServiceExceptionMessage.VALIDATE_NEGATIVE_OR_NULL_NUMBER.getErrorMessage(), parameter, parameter, id));
        }
    }
}
