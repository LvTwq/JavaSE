package com.example.io;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.text.UnicodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 吕茂陈
 * @date 2022/02/16 17:35
 */
@Slf4j
public class Base64Test {

	@Test
	public void encodeFile2Base64() {
		File file = new File("spFilePath/extObj.txt");
		String encode = Base64.encode(file);
		log.info("{}", encode);
	}


	@Test
	public void urlencode() {
//		String str = "Microsoft%20Windows%2011%20%5Cu5bb6%5Cu5ead%5Cu4e2d%5Cu6587%5Cu7248";
		String str = null;
		String str1 = URLDecoder.decode(str, StandardCharsets.UTF_8);
		log.info(str1);
		String str2 = UnicodeUtil.toString(str1);
		log.info(str2);
		String str3 = UnicodeUtil.toString(URLDecoder.decode(str, StandardCharsets.UTF_8));
		log.info(str3);
	}


	@Test
	public void test01() {
		String s = Base64Decoder.decodeStr("KGludGVnZXIpIDAN");
		String s1 = Base64Decoder.decodeStr("KGludGVnZXIpIDA=");
		System.out.println("1");
	}
}
