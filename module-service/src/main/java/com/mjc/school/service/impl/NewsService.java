package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.ResourceNotFoundException;
import com.mjc.school.service.exception.ServiceExceptionMessage;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return newsRepository.readAll().stream()
                .map(newsMapper::newsToDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        //
        Optional<NewsModel> newsModelOptional = newsRepository.readById(id);
        return newsMapper.newsToDto(
                newsModelOptional.orElseThrow(() ->
                        new ResourceNotFoundException(String.format(ServiceExceptionMessage.NEWS_ID_DOES_NOT_EXIST.getErrorMessage(), id)))
        );
    }

    @Override
    @Validate
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        //
        NewsModel newsModel = newsMapper.newsDtoToModel(createRequest);
        NewsModel newNewsModel = newsRepository.create(newsModel);
        return newsMapper.newsToDto(newNewsModel);
    }

    @Override
    @Validate
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        //
        NewsModel updatedNews = newsMapper.newsDtoToModel(updateRequest);
        NewsModel newsModel = newsRepository.readById(updatedNews.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format(ServiceExceptionMessage.NEWS_ID_DOES_NOT_EXIST.getErrorMessage(), updatedNews.getId()))
                );
        newsModel.setTitle(updateRequest.getTitle());
        newsModel.setContent(updatedNews.getContent());
        return newsMapper.newsToDto(newsRepository.update(newsModel));
    }

    @Override
    public boolean deleteById(Long id) {
        //
        if (!newsRepository.existById(id)) {
            throw new ResourceNotFoundException(String.format(ServiceExceptionMessage.NEWS_ID_DOES_NOT_EXIST.getErrorMessage(), id));
        }
        return newsRepository.deleteById(id);
    }
}
