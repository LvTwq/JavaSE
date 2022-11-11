package com.example.net;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 * @date 2022/02/08 16:56
 */
@Slf4j
public class HttpClient {

	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();
		Request request = new Request.Builder()
				.url("http://101.34.231.204:8888/testimony/api/v1/file/3ec0949916a240ddb9092e14e4bd0cef/download?type=1")
				.method("GET", null)
				.addHeader("Authorization", "bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJzY29wZV9jb3JlIl0sImV4cCI6MTY0NDQxNDY0MSwianRpIjoiZTllYWRlZTUtZDk4Ny00NDY1LTg3NmMtNDQ1NDE1ODUyNTg5IiwiY2xpZW50X2lkIjoicHJpdmF0ZV9zZXJ2aWNlX2dyb3VwIn0.c_xw4aMNaQx9kMZk1yyTA76DwvAGLPYDFOi3bPe38Mc")
				.build();
		try (Response response = client.newCall(request).execute();
			 ResponseBody body = response.body()) {

			String string = body.toString();
			log.info(string);

			byte[] bytes = body.bytes();
			log.info("{}", bytes.length);

			InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			log.info("{}", byteArrayInputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

//        File tempFile = File.createTempFile("123", "png", new File("E:\\Code\\JavaSE\\src\\main\\resources"));
//            tempFile.deleteOnExit();
//            FileOutputStream out = new FileOutputStream(tempFile);
//            IOUtils.copy(inputStream, out);
	}


	@Test
	public void test01() {
		File file = new File("D:\\1.png");
		String str = JSONUtil.toJsonStr(FileInfo.builder().id("123").type("enclient").build());
		log.info(str);
		Map<String, Object> map = Map.of("file", file, "fileInfo", str);
		String post = HttpUtil.post("http://localhost:8001/filecenter/upload", map);
		log.info(post);
	}


	@Test
	public void test() throws InterruptedException {
		int clientSize = 15000;
		CountDownLatch downLatch = new CountDownLatch(clientSize);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(clientSize);
		IntStream.range(0, clientSize).forEach(i ->
				fixedThreadPool.submit(() -> {
					RestTemplate restTemplate = new RestTemplate();
					String result = restTemplate.getForObject("http://localhost/lmc/task2", String.class);
					log.info(result);
					downLatch.countDown();
				})
		);
		downLatch.await();
		fixedThreadPool.shutdown();
	}
}
