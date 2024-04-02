package com.example.string;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 吕茂陈
 */
@Slf4j
public class StringTest {

	public static void main(String[] args) {
		String s = "xyz";
		String s3 = getString().intern();
		String s1 = new String("xyz");
		String s2 = new String("xyz");
		log.info("s1和s是否相等：{}", s1.equals(s));
		log.info("s1和s是否同一对象：{}", s == s1);
		log.info("s1和s2是否同一对象：{}", s2 == s1);
	}


	private static String getString() {
		return "xyz";
	}


	@Test
	public void test01() {
//        String str = "https://cloud.xylink.com/api/rest/external/v1/vods/9308673/getdownloadurl?enterpriseId=50c76142608a6f8d4d1a592f212d3b15165e01a6&signature=fX5PYU8wzcAef7cNQe6pV2LkMz00WAdZOnHnt1CJpE0%3D";
		String str = "http://101.34.231.204:8888/testimony/api/v1/file/xxxxxxxxxxxxxx/download?type=1";
		String[] split = str.split("testimony", 0);
		for (String s : split) {
			log.info(s);
		}
	}

	@Test
	public void test03() {
		String[] split = StringUtils.split("安徽省-合肥市-瑶海区", "-");
		log.info(split[split.length - 1]);
	}

	@Test
	public void test02() {
		String str = "aaaaa,bbbbb,ccccccc";
		log.info(str.substring(str.indexOf(",") + 1));
	}


	@Test
	public void test04() {
		Integer i = null;
		log.info("{}", i.equals("1"));
	}


	@Test
	public void test05() {
		// true
		log.info("{}", null == null);
		Integer i = null;
		// true
		log.info("{}", i == null);
	}

	@Test
	public void test06() {
		int i1 = 1;
		Integer i2 = 1;
		String s = "1";
		log.info("{}", ObjectUtil.equals(i1, i2));
	}


	private static final String s = "11234";

	@Test
	public void test07() {
		String s = "Test";
		String s1 = s.toUpperCase();
		log.info("{}", s);
		log.info("{}", s1);
	}


	@Test
	public void test08() {
		System.out.println(1 + 2 + "," + 3 + 4);
	}


	@Test
	public void test09() {
		// a 是一个引用
		String a = new String("ab");
		// b 是另一个引用，对象内容一样
		String b = new String("ab");
		// 放在常量池中
		String aa = "ab";
		// 从常量池中查找
		String bb = "ab";
		if (aa == bb) {
			System.out.println("aa==bb");
		}
		if (a == b) {
			System.out.println("a==b");
		}
		if (a.equals(b)) {
			System.out.println("a EQ b");
		}

		System.out.println(42 == 42.0);

		// 使用常量池中的对象
		Integer i1 = 40;
		// 创建新对象
		Integer i2 = new Integer(40);
	}


	@Test
	public void test10 (){
		String str = "111";
		Object str1 = (Object)str;
		log.info("{}", str1);
	}


	@Test
	public void test11 (){
		String sql = "select metrics_name,label1, sum(sample_value) as sample_value\n"
				+ "from tb_pre_processing_info\n"
				+ "where metrics_name = '" +  "'\n"
				+ "  and time_flag >= '"  + "'\n"
				+ "  and time_flag < '" + "'\t"
				+ "group by label1\r"
				+ "order by sum(sample_value) desc\n"
				+ "limit ";
		String replaceJson = StringUtils.replace(sql, "\t", " ")
				.replace("\n", " ").replace("\r", " ");
		System.out.println(replaceJson);
//		String replace = StrUtil.replace(sql, "\\r|\\n|\\t", parameters -> "-" + parameters.group(1) + "-");
//		System.out.println(replace);
	}



	@Test
	public void test12() {
		String str = "3E31B7E7EB44E273B41EC9FE23F7A2E1|query";
		String[] split = str.split("|");
		System.out.println(split[0]);
		System.out.println(split[1]);
		String[] split1 = StringUtils.split(str, "|");
		System.out.println(split1[0]);

	}

	@Test
	public void test13() {
		String str = "/client/";

		String[] split = StringUtils.split(str, "/");
		System.out.println(split.length);
	}


	@Test
	public void test14() {
		System.out.println(StringUtils.equals("0", null));

		String str = Stream.of("美国", "0", "0")
				.filter(e -> StringUtils.isNotBlank(e) && !StringUtils.equals("0", e)).collect(Collectors.joining("-"));
		System.out.println(str);
	}



	@Test
	public void test15() {
		System.out.println(isInnerIp("10.10.72.118"));
	}


	public boolean isInnerIp(String ipAddress) {
		String INNER_IP = "10.0.0.0-10.255.255.255;172.16.0.0-172.31.255.255;10.10.0.0-10.10.255.255";


		long ipNum = NetUtil.ipv4ToLong(ipAddress);

		for (String str : StringUtils.split(INNER_IP, ";")) {
			String[] split = StringUtils.split(str, "-");
			long begin = NetUtil.ipv4ToLong(split[0]);
			long end = NetUtil.ipv4ToLong(split[1]);
			if ((ipNum >= begin) && (ipNum <= end)) {
				return true;
			}
		}
		return false;
	}


	String reg = "(\\[(.*?)\\])";
	@Test
	public void test16() {
		String temp = "ModSecurity: Warning. Matched \"Operator `Within' with parameter `' against variable `REQUEST_PROTOCOL' (Value: `HTTP/1.1' ) [file \"/lvmc-plus/lvmc/appgateway/modsec/././ruleset/owasp-modsecurity-crs-3.0.2/rules/REQUEST-920-PROTOCOL-ENFORCEMENT.conf\"] [line \"1015\"] [id \"920430\"] [rev \"2\"] [msg \"HTTP protocol version is not allowed by policy\"] [data \"HTTP/1.1\"] [severity \"2\"] [ver \"OWASP_CRS/3.0.0\"] [maturity \"9\"] [accuracy \"9\"] [tag \"application-multi\"] [tag \"language-multi\"] [tag \"platform-multi\"] [tag \"attack-protocol\"] [tag \"OWASP_CRS/POLICY/PROTOCOL_NOT_ALLOWED\"] [tag \"WASCTC/WASC-21\"] [tag \"OWASP_TOP_10/A6\"] [tag \"PCI/6.5.10\"] [hostname \"10.10.112.157\"] [uri \"/js/jquery.fireworks.js\"] [unique_id \"169829003057.053411\"] [ref \"v28,8\"]";
		String[] strings = temp.split(" ");
		String date = strings[0].substring(0, 7);
		date = date.replaceAll("/", ".");
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(temp);
		JSONObject jsonObject = new JSONObject();
		// 使用正则表达式捕获组来匹配 ModSecurity: 和 . 之间的字符串
		Pattern actionPattern = Pattern.compile("ModSecurity: (.*?)\\.");
		Matcher actionMatcher = actionPattern.matcher(temp);

		if (actionMatcher.find()) {
			String result = actionMatcher.group(1);
			jsonObject.set("action" , result);
		}

		while (matcher.find()){
			String str = matcher.group(1);
			String result = StrUtil.unWrap(str,"[","]");
			String ttt = StrUtil.subBefore(result," ",false);
			String ddd = StrUtil.subAfter(result," ",false);
			if (StrUtil.isNotBlank(ddd)){
				ddd = StrUtil.removePrefix(ddd,"\"");
				ddd = StrUtil.removeSuffix(ddd,"\"");
				if (ttt.equals("file")){
					String[] strings1 = ddd.split("/");
					jsonObject.set(ttt,strings1[strings1.length-1]);
				}else {
					jsonObject.set(ttt,ddd);
				}

			}
		}
		Pattern pattern1 = Pattern.compile("(server: (.*?),)");
		Matcher matcher1 = pattern1.matcher(temp);
		if (matcher1.find()){
			String str = matcher1.group(1);
			String ddd = StrUtil.subAfter(str,": ",false);
			ddd = StrUtil.removeSuffix(ddd,",");
			jsonObject.set("server",ddd);
		}
		Pattern pattern2 = Pattern.compile("(host: \"(.*?)\")");
		Matcher matcher2 = pattern2.matcher(temp);
		if(matcher2.find()){
			String str = matcher2.group(1);
			String ddd = StrUtil.subAfter(str,": ",false);
			ddd = StrUtil.removeAll(ddd, "\"");
			jsonObject.set("host",ddd);
		}
		String timeStr = temp.substring(0, 19);

	}


	@Test
	public void test17() {
		String s1 = null + "111";
		System.out.println(s1);
	}

	@Test
	public void test18() {
		String[] split = StringUtils.split("/http/webvpn65ae4657ea6026e6d2138a5133f4aa6e6e185a9cf028a531695921b69ea0fd35", "/");
		System.out.println(split);
	}

}
