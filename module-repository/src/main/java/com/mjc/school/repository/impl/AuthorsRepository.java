package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.aspects.OnDelete;
import com.mjc.school.repository.dataSource.DataSource;
import com.mjc.school.repository.model.impl.AuthorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorsRepository implements BaseRepository<AuthorModel, Long> {
    //
    private final DataSource dataSource;

    @Override
    public List<AuthorModel> readAll() {
        //
        return dataSource.getAuthorModelList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        //
        return dataSource.getAuthorModelList().stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
    }

    @Override
    public AuthorModel create(AuthorModel authorModel) {
        //
        Long nextId = dataSource.getAuthorModelList().stream()
                .mapToLong(AuthorModel::getId)
                .max()
                .orElse(0L) + 1;
        authorModel.setId(nextId);
        this.dataSource.getAuthorModelList().add(authorModel);
        return authorModel;
    }

    @Override
    public AuthorModel update(AuthorModel updatedAuthorModel) {
        //
        for (int i = 0; i < dataSource.getAuthorModelList().size(); i++) {
            if (dataSource.getAuthorModelList().get(i).getId().equals(updatedAuthorModel.getId())) {
                dataSource.getAuthorModelList().set(i, updatedAuthorModel);
                return updatedAuthorModel;
            }
        }
        return null;
    }

    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        //
        return dataSource.getAuthorModelList().removeIf(author -> author.getId().equals(id));
    }

    @Override
    public boolean existById(Long id) {
        //
        return dataSource.getAuthorModelList().stream()
                .anyMatch(news -> news.getId().equals(id));
    }
}
