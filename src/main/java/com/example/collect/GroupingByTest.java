package com.example.collect;

import cn.hutool.json.JSONUtil;
import com.example.lombok.Student;
import com.example.oop.Person;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 吕茂陈
 * @description Collectors.groupingBy根据一个或多个属性对集合中的项目进行分组
 * @date 2023/2/1 16:42
 */
@Slf4j
public class GroupingByTest {

    @Test
    public void test01() {
        List<Student> list = new ArrayList<>();
        list.add(null);
        log.info("list 是否为空：{}", CollectionUtils.isEmpty(list));

        // 按 age 分组，value为student的id的list
        Map<Integer, List<Long>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getId, Collectors.toList())));

    }

    @Data
    public class Product {
        Long id;
        Integer num;
        BigDecimal price;
        String name;
        String category;

        public Product(Long id, Integer num, BigDecimal price, String name, String category) {
            this.id = id;
            this.num = num;
            this.price = price;
            this.name = name;
            this.category = category;
        }
    }


    @Test
    public void test02() {
        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        Product prod6 = new Product(5L, 10, new BigDecimal("11"), "xx啤酒", null);
        List<Product> prodList = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5);

        // 按照类目分组
        Map<String, List<Product>> prodMap = prodList.stream().collect(Collectors.groupingBy(Product::getCategory));
        log.info("按照类目分组:{}", JSONUtil.toJsonPrettyStr(prodMap));

        // 按照几个属性拼接分组
        Map<String, List<Product>> prodMap1 = prodList.stream().collect(Collectors.groupingBy(item -> item.getCategory() + "_" + item.getName()));
        log.info("按照几个属性拼接分组:{}", JSONUtil.toJsonPrettyStr(prodMap1));


        // 根据不同条件分组
        Map<String, List<Product>> prodMap2 = prodList.stream().collect(Collectors.groupingBy(item -> {
            if (item.getNum() < 3) {
                return "3";
            } else {
                return "other";
            }
        }));
        log.info("根据不同条件分组:{}", JSONUtil.toJsonPrettyStr(prodMap2));

        // 多级分组
        Map<String, Map<String, List<Product>>> prodMap3 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.groupingBy(item -> {
            if (item.getNum() < 3) {
                return "3";
            } else {
                return "other";
            }
        })));
        log.info("根据不同条件分组:{}", JSONUtil.toJsonPrettyStr(prodMap3));


        // 按子组收集数据
        Map<String, Long> prodMap4 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        log.info("按子组收集数据-求总数:{}", JSONUtil.toJsonPrettyStr(prodMap4));


        Map<String, Integer> prodMap5 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getNum)));
        log.info("按子组收集数据-求和:{}", JSONUtil.toJsonPrettyStr(prodMap5));


        Map<String, Product> prodMap6 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));
        log.info("按子组收集数据-把收集器的结果转换为另一种类型:{}", JSONUtil.toJsonPrettyStr(prodMap6));


        Map<String, Set<String>> prodMap7 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toSet())));
        log.info("按子组收集数据-联合其他收集器:{}", JSONUtil.toJsonPrettyStr(prodMap7));


        Map<String, Set<Person>> prodMap8 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.mapping(product -> Person.builder().name(product.getName()).age(product.getNum()).build(), Collectors.toSet())));
        log.info("按子组收集数据-联合其他收集器(把 value 的 List<obj1> 转为 List<obj2>):{}", JSONUtil.toJsonPrettyStr(prodMap8));

        List<Person> collect = prodList.stream().collect(Collectors.mapping(product -> Person.builder().name(product.getName()).age(product.getNum()).build(), Collectors.toList()));
        log.info("转换:{}", JSONUtil.toJsonPrettyStr(collect));

    }


    @Data
    @Builder
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    static class DataObject {
        String deviceId;
        String userId;
        String createTime;
    }

    @Test
    public void test03() {
        List<DataObject> dataObjects = List.of(
                DataObject.builder().deviceId("1111").createTime("2023-08-18 16:38:48").build(),
                DataObject.builder().deviceId("1111").createTime("2024-08-18 16:38:48").build(),
                DataObject.builder().deviceId("1111").createTime("2023-08-17 16:38:48").build(),
                DataObject.builder().deviceId("1111").createTime("2023-08-17 16:38:49").build(),
                DataObject.builder().deviceId("1111").createTime("2023-08-17 16:39:48").build()
        );

        Map<String, List<DataObject>> groupedData = dataObjects.stream()
                .sorted(Comparator.comparing(DataObject::getCreateTime))
                .collect(Collectors.groupingBy(DataObject::getDeviceId));
        System.out.println(groupedData);
    }


    @Test
    public void test04() {
        Map<String, Set<String>> appOsMap = Map.of(
                "1", Set.of("windows", "ios"),
                "2", Set.of("mac"),
                "3", Set.of("ios"),
                "all", Set.of("all"),
        "4", Set.of("linux"));

        List<ServiceInfoModel> serviceList = List.of(new ServiceInfoModel("1", "baidu"),
                new ServiceInfoModel("2", "baidu"),
                new ServiceInfoModel("3", "zhihu"),
                new ServiceInfoModel("4", "zhihu"));

        Map<String, Set<String>> collect = serviceList.stream()
                .collect(Collectors.groupingBy(ServiceInfoModel::getServer,
                        Collectors.mapping(new Function<ServiceInfoModel, Set<String>>() {
                            @Override
                            public Set<String> apply(ServiceInfoModel serviceInfoModel) {
                                String serverId = serviceInfoModel.getId();

                                return appOsMap.getOrDefault(serverId, new HashSet<>());
                            }
                        }, Collectors.reducing(new HashSet<String>(), (set1, set2) -> {
                            set1.addAll(set2);
                            return set1;
                        }))));
        System.out.println(collect);

        Map<String, Set<String>> resultMap = serviceList.stream()
                .flatMap(service -> appOsMap.getOrDefault(service.getId(), new HashSet<>()).stream().map(os ->
                        new AbstractMap.SimpleEntry<>(service.getServer(), os)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));

        // 输出 resultMap
        System.out.println(resultMap);

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ServiceInfoModel {
        private String id;
        private String server;
    }

}
