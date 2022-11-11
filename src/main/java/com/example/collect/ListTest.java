package com.example.collect;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.net.ClientInfo;
import com.example.net.FileInfo;
import com.example.lombok.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


	@Test
	public void test02() throws IOException {
		String command1 = "echo 'nameserver 168.168.168.55' >> /etc/resolv.conf";
		String command2 = "systemctl restart dnsmasq";
		log.info("{}", List.of(command1, command2));
		ObjectMapper objectMapper = new ObjectMapper();
		log.info("{}", objectMapper.writeValueAsString(List.of(command1, command2)));
		String jsonStr = JSONUtil.toJsonStr(List.of(command1, command2));
		log.info("{}", jsonStr);

		String str = HttpUtil.get("http://168.168.168.55:8501/dns/select");
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
	public void test04() {
		List<FileInfo> infoList = List.of(
				FileInfo.builder().os("Windows7/Windows8/Windows10").bit("32/64").build(),
				FileInfo.builder().os("Windows11").bit("32/64").build()
		);
		List<Object> collect = infoList.stream()
				.flatMap(info -> {
					Arrays.stream(info.getOs().split("/")).flatMap(os -> {
						Arrays.stream(info.getBit().split("/")).map(bit -> {
							return ClientInfo.builder()
									.os(os)
									.bit(bit)
									.downloadUrl(info.getDownloadUrl())
									.build();
						}).collect(Collectors.toList());
						return null;
					}).collect(Collectors.toList());
					return null;
				}).collect(Collectors.toList());
		log.info(JSONUtil.toJsonStr(collect));
	}

	@Test
	public void test06() {
		List<FileInfo> infoList = List.of(
				FileInfo.builder().os("Windows7/Windows8/Windows10").bit("32/64").build(),
				FileInfo.builder().os("Windows11").bit("32/64").build()
		);
		List<FileInfo> collect = infoList.stream()
				.flatMap(info -> Arrays.stream(info.getOs().split("/")).map(os -> FileInfo.builder().os(os).bit(info.getBit()).build()))
//				.flatMap(info -> Arrays.stream(info.getOs().split("/")).map(os -> info.setOs(os).setBit(info.getBit())))
				.flatMap(info -> Arrays.stream(info.getBit().split("/")).map(bit -> FileInfo.builder().os(info.getOs()).bit(bit).build()))
//				.flatMap(info -> Arrays.stream(info.getBit().split("/")).map(bit -> info.setOs(info.getOs()).setBit(bit)))
				.collect(Collectors.toList());
		log.info(JSONUtil.toJsonStr(collect));
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


}


