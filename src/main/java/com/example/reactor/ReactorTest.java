package com.example.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author 吕茂陈
 * @date 2022-07-20 11:47
 */
@Slf4j
public class ReactorTest {

    List<String> words = List.of("the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog");


    @Test
    public void simpleCreation() {
        // 把 List 作为一个整体触发
        Flux<String> fewWords = Flux.just("hello", "world");
        // 逐个触发 List 里的每个元素
        Flux<String> manyWords = Flux.fromIterable(words);

        fewWords.subscribe(log::info);
        log.info("=========================");
        manyWords.subscribe(log::info);
    }

    @Test
    public void findingMissingLetter() {
        Flux<String> manyLetters = Flux.fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));
        manyLetters.subscribe(log::info);
    }
}
