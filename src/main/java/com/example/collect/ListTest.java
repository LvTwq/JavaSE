package com.example.collect;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.net.FileInfo;
import com.example.oop.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 */
@Slf4j
public class ListTest {


    @Test
    public void test01(){
        Object list = List.of("1","2","3");
        List<String> a = (List<String>)list;
        List<String> b = BeanUtil.toBean(list, List.class);
    }




    @Test
    public void test02() throws IOException {
        String command1 = "echo 'nameserver 10.10.168.55' >> /etc/resolv.conf";
        String command2 = "systemctl restart dnsmasq";
        log.info("{}", List.of(command1, command2));
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("{}", objectMapper.writeValueAsString(List.of(command1, command2)));
        String jsonStr = JSONUtil.toJsonStr(List.of(command1, command2));
        log.info("{}", jsonStr);

        String str = HttpUtil.get("http://10.10.168.55:8501/dns/select");
        List<String> list = objectMapper.readValue(str, new TypeReference<>() {
        });
        log.info("{}", str);
        log.info("{}", list);
    }


    @Test
    public void test03() {
        List<String> origin = List.of("1", "2", "3", "4");
        List<String> current = List.of("2", "3", "4", "5");
        HashSet<String> union = new HashSet<>(origin);
        union.addAll(current);
        List<String> collect = union.stream().map(e -> {
            if (origin.contains(e) && !current.contains(e)) {
                return "减少" + e;
            } else if (!origin.contains(e) && current.contains(e)) {
                return "增加" + e;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        log.info("{}", collect);

    }







    @Test
    public void test07() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileInfo info = FileInfo.builder().os("Windows10").bit(objectMapper.writeValueAsString(List.of())).build();
        List<String> str = objectMapper.readValue(info.getBit(), new TypeReference<>() {
        });
        log.info("{}", str);
    }


    @Test
    public void test08() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        String join = StringUtils.join(list, "','");
        log.info(join);
    }


    @Test
    public void test09() {
        String str = "/南京/雨花台/怡华";
        String substring = str.substring(str.lastIndexOf("/"));
        String substring1 = str.substring(0, str.lastIndexOf("/") + 1);
        String substring2 = str.substring(str.indexOf("/", 2));
        String substring3 = str.substring(str.indexOf("/") + 1);
        log.info(substring);
        log.info(substring1);
        log.info(substring2);
        log.info(substring3);
    }


    @Test
    public void test10() {
        List<String> collect = IntStream.rangeClosed(0, 10).boxed().map(String::valueOf).collect(Collectors.toList());
        for (String s : collect) {
            if ("5".equals(s)) {
                collect = collect.stream().filter(e -> !StringUtils.equals(s, e)).collect(Collectors.toList());
            }
        }
        log.info("{}", collect);
    }

    @Test
    public void test11() {
        Person person = Person.builder().name("lmc").build();
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            personList.add(person.setAge(i));
        }
        log.info("{}", personList);
    }

    @Test
    public void test12() {
        Map<String, String> map = List.of("ACCESSBEHAVIOR_WEBAPPACCESS",
                "ACCESSBEHAVIOR_OPERATIONS",
                "ACCESSBEHAVIOR_NETWORK_TUNNELAPPACCESS",
//            "ACCESSBEHAVIOR_SPA",
                "ACCESSBEHAVIOR_TUNNELLOGIN",
                "USER_CLIENT_DNSRESOLVE").stream().collect(Collectors.toMap(Function.identity(), t -> "true"));
        log.info("{}", JSONUtil.toJsonStr(map));
    }



    @Test
    public void test13 () {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> integers = list.subList(0, 3);
        System.out.println(integers);
        System.out.println(list);
        System.out.println(list.subList(0, list.size()));
        // 假如丢弃3行，
        System.out.println(list.subList(5-3, list.size()));
        integers.clear();

        // 输出删除前几个元素后的列表
        System.out.println(list);
        System.out.println(list.subList(0, list.size()));
    }


    @Test
    public void test14() {
    List<String> policyList = List.of("timepolicy");
    String join = StringUtils.join(policyList, "=false&");
    System.out.println(join);

        String collect = policyList.stream().collect(Collectors.joining("=false&"));
        System.out.println(collect);

        String collect1 = policyList.stream().map(e -> e + "=false&").collect(Collectors.joining());
        System.out.println(collect1);

        String url = "https://10.10.113.122:58679/enfilecenter/api/filecenter/download/202309181812212761703713561878208512";
            StringBuilder queryString = new StringBuilder(url).append("?");
            policyList.forEach(e -> queryString.append(e).append("=false&"));
            if (queryString.length() > 0) {
                queryString.deleteCharAt(queryString.length() - 1);
            }
            url = queryString.toString();

        System.out.println(queryString);
    }


    @Test
    public void test15() {
        List<Person> list = List.of(Person.builder().name("张三").age(18).build());
        list.stream().map(vo -> {
            vo.setName("李四");
            return vo;
        }).collect(Collectors.toList());
        System.out.println(list);
    }


    @Test
    public void test16() {
        String join = String.join("\",\"", List.of("123", "789"));
        System.out.println(join);
    }

}


