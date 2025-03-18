package com.mjc.school.repository.aspects;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OnDeleteAspect {
    //
    BaseRepository<NewsModel, Long> newsRepository;

    @Autowired
    public OnDeleteAspect(NewsRepository newsRepository) {
        //
        this.newsRepository = newsRepository;
    }

    @Pointcut("within(com.mjc.school.repository.impl.AuthorsRepository) && @annotation(com.mjc.school.repository.aspects.OnDelete)")
    public void methodTMP() {
        //
    }

    @After(value = "methodTMP()&& args(id)")
    public void onDelete(Long id) {
        //
        newsRepository.readAll().removeIf(newsModel -> newsModel.getAuthorId().equals(id));
    }

}
