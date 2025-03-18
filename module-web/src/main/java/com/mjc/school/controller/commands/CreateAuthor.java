package com.mjc.school.controller.commands;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.exceptions.ValidationException;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import java.util.Scanner;

public class CreateAuthor implements BaseCommand {
    BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController;

    public CreateAuthor(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> authorController) {
        this.authorController = authorController;
    }

    @Override
    public void execute() {
        boolean isTrue = false;
        while (!isTrue) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Write author name:");
                String tmpName = scanner.nextLine();
                AuthorDtoRequest authorDtoRequest = AuthorDtoRequest.builder()
                        .name(tmpName)
                        .build();
                System.out.println(authorController.create(authorDtoRequest));
                isTrue = true;
            } catch (ValidationException e) {
                throw new ValidationException("Author name is invalid");
            }
        }

    }
}
