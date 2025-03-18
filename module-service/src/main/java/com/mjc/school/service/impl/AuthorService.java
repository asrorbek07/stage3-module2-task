package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validation.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    //
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Override
    public List<AuthorDtoResponse> readAll() {
        //
        return authorRepository.readAll().stream().map(authorMapper::authorModelToDto).toList();
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        //
        Optional<AuthorModel> authorModel = authorRepository.readById(id);
        if (authorModel.isPresent()) return authorMapper.authorModelToDto(authorModel.get());
        else throw new RuntimeException("Author with provided id does not exist");
    }

    @Override
    @Validate
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        //
        return authorMapper.authorModelToDto(authorRepository.create(authorMapper.authorDtoToModel(createRequest)));
    }

    @Override
    @Validate
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        //
        return authorMapper.authorModelToDto(authorRepository.update(authorMapper.authorDtoToModel(updateRequest)));
    }

    @Override
    public boolean deleteById(Long id) {
        //
        return authorRepository.deleteById(id);
    }
}
