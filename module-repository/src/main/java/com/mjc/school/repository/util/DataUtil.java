package com.mjc.school.repository.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class DataUtil {
    //
    public LocalDateTime now() {
        //
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    }
}
