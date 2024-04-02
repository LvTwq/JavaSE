package com.example.collect;

import cn.hutool.json.JSONUtil;
import com.example.net.FileInfo;
import com.example.oop.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/2/1 16:16
 */
@Slf4j
public class FlatMapTest {


    @Test
    public void test06() {
        List<FileInfo> infoList = List.of(
                FileInfo.builder().os("Windows7/Windows8/Windows10").bit("32/64").build(),
                FileInfo.builder().os("Windows11").bit("32/64").build()
        );
        // flatMap() 产生一个新值流，而对于 map()，它为每个输入元素生成一个值
        List<FileInfo> collect = infoList.stream()
                .flatMap(info -> Arrays.stream(info.getOs().split("/")).map(os -> FileInfo.builder().os(os).bit(info.getBit()).build()))
                .flatMap(info -> Arrays.stream(info.getBit().split("/")).map(bit -> FileInfo.builder().os(info.getOs()).bit(bit).build()))
                .collect(Collectors.toList());
        log.info(JSONUtil.toJsonStr(collect));

        List<FileInfo> collect1 = infoList.stream()
                .flatMap(info -> Arrays.stream(info.getOs().split("/")).map(os -> info.setOs(os).setBit(info.getBit())))
                .flatMap(info -> Arrays.stream(info.getBit().split("/")).map(bit -> info.setOs(info.getOs()).setBit(bit)))
                .collect(Collectors.toList());
        log.info(JSONUtil.toJsonStr(collect1));
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EntityMp {

        private String entityId;

        private List<String> tagIds;
        private String tagId;
        private List<Person> personList;
    }


    @Test
    public void test14() {
        List<EntityMp> list = Stream.of(
                        EntityMp.builder().entityId("entity01").tagIds(List.of("01", "02")).build(),
                        EntityMp.builder().entityId("entity02").tagIds(List.of("03", "04")).build()
                )
                .flatMap(info -> info.getTagIds().stream().map(tagId -> EntityMp.builder().entityId(info.getEntityId()).tagId(tagId).build()))
                .collect(Collectors.toList());
        log.info("{}", list);
    }


    @Test
    public void test13() {
        List<List<String>> lists = List.of(
                List.of("1", "2"), List.of("3")
        );
        // [[1, 2], [3]]
        Set<List<String>> collect = Stream.of(lists).flatMap(Collection::stream).collect(Collectors.toSet());
        log.info("collect: {}", collect);

        // [1, 2, 3]
        Set<String> collect1 = Stream.of(List.of("1", "2"), List.of("3")).flatMap(strings -> strings.stream()).collect(Collectors.toSet());
        log.info("collect1: {}", collect1);

        Set<String> collect2 = lists.stream().flatMap(Collection::stream).collect(Collectors.toSet());
        // [1, 2, 3]
        log.info("collect2: {}", collect2);


        Object[] objects = lists.toArray();
        for (Object object : objects) {
            log.info("object:{}", object);
        }
        Spliterator<Object> spliterator = Spliterators.spliterator(objects, 0);
        List<Object> list = Stream.of(objects).flatMap(obj -> StreamSupport.stream(spliterator, false)).collect(Collectors.toList());
        log.info("{}", list);

    }


    @Test
    public void test01() {
        Person person1 = Person.builder().name("1").build();
        Person person2 = Person.builder().name("2").build();
        Person person3 = Person.builder().name("3").build();
        Person person4 = Person.builder().name("4").build();
        List<EntityMp> mps = List.of(
                EntityMp.builder().personList(List.of(person1)).build(),
                EntityMp.builder().personList(List.of(person1, person2, person3, person4)).build()
        );
        List<String> collect = mps.stream().map(mp -> {
            List<Person> personList = mp.getPersonList();
            return personList.stream().map(Person::getName).collect(Collectors.toList());
        }).flatMap(Collection::stream).collect(Collectors.toList());
        log.info("{}", collect);
    }



    @Test
    public void test02() {
        Person person1 = Person.builder().name("10").age(1).build();
        Person person2 = Person.builder().name("20").age(2).build();
        List<String> collect = Stream.of(person1, person2).flatMap(e -> Stream.of(e.getName(), String.valueOf(e.getAge())))
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void test03() {
        Person person1 = Person.builder().name("10,5").age(1).build();
        Person person2 = Person.builder().name("20").age(2).build();
        List<String> collect = Stream.of(person1, person2).flatMap(e -> Arrays.stream(e.getName().split(",")))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
