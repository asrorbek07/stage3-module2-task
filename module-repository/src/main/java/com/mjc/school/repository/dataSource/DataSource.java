package com.mjc.school.repository.dataSource;

import com.mjc.school.repository.exception.ExceptionMessage;
import com.mjc.school.repository.exception.FailedToLoadFileException;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSource {
    //
    private final String AUTHOR_FILE = "author.txt";
    private final String NEWS_FILE = "news.txt";
    private final String CONTENT_FILE = "content.txt";
    private final int NEWS_COUNT = 20;

    @Getter
    private List<AuthorModel> authorModelList = generateAuthors();
    @Getter
    private List<NewsModel> newsModelList = generateNews();

    private List<AuthorModel> generateAuthors() {
        //
        List<String> authorLines = readResourceFile(AUTHOR_FILE);
        List<AuthorModel> authorModels = new ArrayList<>();
        long id = 1;
        for (String name : authorLines) {
            authorModels.add(AuthorModel.builder()
                    .id(id++)
                    .name(name.trim())
                    .createDate(LocalDateTime.now())
                    .lastUpdateDate(LocalDateTime.now())
                    .build());
        }
        return authorModels;
    }

    private List<NewsModel> generateNews() {
        //
        List<String> newsTitles = readResourceFile(NEWS_FILE);
        List<String> contentLines = readResourceFile(CONTENT_FILE);
        List<NewsModel> newsModelList = new ArrayList<>();
        Random random = new Random();

        for (long id = 1; id <= NEWS_COUNT; id++) {
            String title = newsTitles.get(random.nextInt(newsTitles.size())).trim();
            String content = contentLines.get(random.nextInt(contentLines.size())).trim();
            Long authorId = id;

            newsModelList.add(NewsModel.builder()
                    .id(id)
                    .title(title)
                    .content(content)
                    .createDate(LocalDateTime.now())
                    .lastUpdateDate(LocalDateTime.now())
                    .authorId(authorId)
                    .build());
        }
        return newsModelList;
    }

    private List<String> readResourceFile(String fileName) {
        //
        List<String> lines = new ArrayList<>();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FailedToLoadFileException("Resource file not found: " + fileName);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new FailedToLoadFileException(String.format(ExceptionMessage.FAIL_TO_LOAD_RESOURCE.getErrorMessage(), fileName));
        }

        while (lines.size() < NEWS_COUNT) {
            lines.addAll(new ArrayList<>(lines));
        }
        return lines.subList(0, NEWS_COUNT);
    }
}
