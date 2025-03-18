package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.ResourceNotFoundException;
import com.mjc.school.service.exception.ServiceExceptionMessage;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validation.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    //
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Override
    public List<AuthorDtoResponse> readAll() {
        //
        return authorRepository.readAll().stream().map(authorMapper::authorModelToDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        //
        Optional<AuthorModel> authorModelOptional = authorRepository.readById(id);
        return authorMapper.authorModelToDto(authorModelOptional.orElseThrow(() -> new ResourceNotFoundException(String.format(ServiceExceptionMessage.AUTHOR_ID_DOES_NOT_EXIST.getErrorMessage(), id))));
    }

    @Override
    @Validate
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        //
        AuthorModel authorModel = authorMapper.authorDtoToModel(createRequest);
        AuthorModel newAuthorModel = authorRepository.create(authorModel);
        return authorMapper.authorModelToDto(newAuthorModel);
    }

    @Override
    @Validate
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        //
        AuthorModel updatedAuthorModel = authorMapper.authorDtoToModel(updateRequest);
        AuthorModel authorModel = authorRepository.readById(updatedAuthorModel.getId()).orElseThrow(() -> new ResourceNotFoundException(String.format(ServiceExceptionMessage.AUTHOR_ID_DOES_NOT_EXIST.getErrorMessage(), updatedAuthorModel.getId())));
        authorModel.setName(updateRequest.getName());
        return authorMapper.authorModelToDto(authorRepository.update(authorModel));
    }

    @Override
    public boolean deleteById(Long id) {
        //
        if (!authorRepository.existById(id)) {
            throw new ResourceNotFoundException(String.format(ServiceExceptionMessage.AUTHOR_ID_DOES_NOT_EXIST.getErrorMessage(), id));
        }
        return authorRepository.deleteById(id);
    }
}
