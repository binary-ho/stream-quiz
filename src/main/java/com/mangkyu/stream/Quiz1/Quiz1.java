package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
            .map(userInfo -> userInfo[1])
            .map(hobbies -> hobbies.split(":"))
            .flatMap(Arrays::stream)
            .map(String::trim)
            .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, Integer::sum));
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
            .filter(userInfo -> userInfo[0].trim().charAt(0) == '정')
            .map(userInfo -> userInfo[1])
            .map(hobbies -> hobbies.split(":"))
            .flatMap(Arrays::stream)
            .map(String::trim)
            .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, Integer::sum));
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        String GOOD = "좋아";

        return csvLines.stream()
            .map(userInfo -> userInfo[2])
            .map(String::trim)
            .map(hobby -> {
                int count = 0;
                int index = 0;
                while (true) {
                    index = hobby.indexOf(GOOD, index);
                    if (index == -1) {
                        break;
                    }
                    count++;
                    index += GOOD.length();
                }
                return count;
            })
            .reduce(0, Integer::sum);
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
