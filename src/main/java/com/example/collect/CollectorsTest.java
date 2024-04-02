package com.example.collect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/2 14:20
 */
@Slf4j
public class CollectorsTest {


    @Test
    public void partitioningByTest() {
// 根据Predicate对输入元素进行分区，并将它们组织成Map <Boolean，List >
        Map<Boolean, List<Integer>> mapPartitionBy = Stream.of(1, 2, 3, 4, 5, 4, 3).collect(Collectors.partitioningBy(x -> x % 2 == 0));
        // {false=[1, 3, 5, 3], true=[2, 4, 4]}
        log.info("{}", mapPartitionBy);

    }
}
