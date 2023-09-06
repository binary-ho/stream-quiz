package com.mangkyu.stream.Quiz5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Quiz5 {

    private static final String[] STRING_ARR = {"aaa", "bb", "c", "dddd"};

    // 5.1 모든 문자열의 길이를 더한 결과를 출력하여라.
    public int quiz1() {
        return Arrays.stream(STRING_ARR)
            .map(String::length)
            .reduce(0, Integer::sum);
    }

    // 5.2 문자열 중에서 가장 긴 것의 길이를 출력하시오.
    public int quiz2() {
        return Arrays.stream(STRING_ARR)
            .map(String::length)
            .reduce(0, Integer::max);
    }

    // 5.3 임의의 로또번호(1~45)를 정렬해서 출력하시오.
    public List<Integer> quiz3() {
        List<Integer> list = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(list);
        return list.stream()
            .limit(6)
            .sorted()
            .collect(Collectors.toList());
    }

    // 5.4 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력하시오.
    public List<Integer[]> quiz4() {
        return IntStream.rangeClosed(1, 6)
            .mapToObj(num1 ->
                IntStream.rangeClosed(1, 6)
                    .filter(num2 -> (num1 + num2) == 6)
                    .mapToObj(num2 -> new Integer[]{num1, num2})
                    .toArray(Integer[][]::new))
            .flatMap(Arrays::stream)
            .collect(Collectors.toList());
    }

}
