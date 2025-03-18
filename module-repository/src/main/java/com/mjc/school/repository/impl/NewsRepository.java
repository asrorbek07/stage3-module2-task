package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.dataSource.DataSource;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    //
    private final DataSource dataSource;

    @Override
    public List<NewsModel> readAll() {
        //
        return dataSource.getNewsModelList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        //
        return dataSource.getNewsModelList().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst();
    }

    @Override
    public NewsModel create(NewsModel newsModel) {
        //
        Long nextId = dataSource.getNewsModelList().stream()
                .mapToLong(NewsModel::getId)
                .max()
                .orElse(0L) + 1;
        newsModel.setId(nextId);
        newsModel.setCreateDate(DataUtil.now());
        newsModel.setLastUpdateDate(DataUtil.now());
        this.dataSource.getNewsModelList().add(newsModel);
        return newsModel;
    }

    @Override
    public NewsModel update(NewsModel updatedNewsModel) {
        //
        updatedNewsModel.setLastUpdateDate(DataUtil.now());
        for (int i = 0; i < dataSource.getNewsModelList().size(); i++) {
            if (dataSource.getNewsModelList().get(i).getId().equals(updatedNewsModel.getId())) {
                dataSource.getNewsModelList().set(i, updatedNewsModel);
                return updatedNewsModel;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        //
        return dataSource.getNewsModelList().removeIf(news -> news.getId().equals(id));
    }

    @Override
    public boolean existById(Long id) {
        //
        return dataSource.getNewsModelList().stream()
                .anyMatch(news -> news.getId().equals(id));
    }
}
