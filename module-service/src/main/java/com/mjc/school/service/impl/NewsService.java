package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    //
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final NewsMapper newsMapper = NewsMapper.INSTANCE;

    @Override
    public List<NewsDtoResponse> readAll() {
        //
        return newsRepository.readAll().stream().map(newsMapper::newsToDto).collect(Collectors.toList());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        //
        Optional<NewsModel> newsModelOptional = newsRepository.readById(id);
        if (newsModelOptional.isPresent()) {
            return newsMapper.newsToDto(newsModelOptional.get());
        } else throw new RuntimeException("No news with such id found");
    }

    @Override
    @Validate
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        //
        NewsModel newsModel = newsMapper.newsDtoToModel(createRequest);
        newsModel.setCreateDate(LocalDateTime.now());
        newsModel.setLastUpdateDate(LocalDateTime.now());
        newsRepository.create(newsModel);
        return newsMapper.newsToDto(newsModel);
    }

    @Override
    @Validate
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        //
        NewsModel updatedNews = newsMapper.newsDtoToModel(updateRequest);
        updatedNews.setLastUpdateDate(LocalDateTime.now());
        updatedNews.setCreateDate(newsRepository.readById(updatedNews.getId()).get().getCreateDate());
        return newsMapper.newsToDto(newsRepository.update(updatedNews));
    }

    @Override
    public boolean deleteById(Long id) {
        //
        return newsRepository.deleteById(id);
    }
}
