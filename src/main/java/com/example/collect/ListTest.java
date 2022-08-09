package com.example.collect;

import com.example.lombok.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ListTest {


    @Test
    public void test01() {
        List<Student> list = new ArrayList<>();
        list.add(null);
        log.info("list 是否为空：{}", CollectionUtils.isEmpty(list));

        // 按 age 分组，value为student的id的list
        Map<Integer, List<Long>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getId, Collectors.toList())));

    }
}


