package com.example.net;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
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
public class HttpClientTest {

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
        String str = JSONUtil.toJsonStr(FileInfo.builder().id("123").centerType("enclient").build());
        log.info(str);
        Map<String, Object> map = Map.of("file", file, "fileInfo", str);
        String post = HttpUtil.post("http://10.10.113.122:8001/filecenter/uploadFile", map);
        log.info(post);
    }
    @Test
    public void test05() {
//        File file = new File("spFilePath/SDP-v3.1.2-3.1.4.001-centos.tar.gz");
        File file = new File("D:/SDP-v3.1.2-3.1.4.001-centos.tar.gz");
        String str = JSONUtil.toJsonStr(FileInfo.builder().id("33333334443333333333").centerType("playStore").name("SDP-v3.1.2-3.1.4.001-centos.tar.gz").build());
        try (InputStream inputStream = new FileInputStream(file)) {
            HttpRequest request = HttpUtil.createPost("http://10.10.113.122:8001/filecenter/uploadFile");
            String result = request.form("file", inputStream, "fileInfo", str)
                    .execute().body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void main() throws IOException {
        String uploadUrl = "http://10.10.113.122:8001/filecenter/uploadFile"; // 替换为实际的上传目标URL
        String filePath = "D:/SDP-v3.1.2-3.1.4.001-centos.tar.gz"; // 要上传的大文件的路径

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File does not exist.");
            return;
        }

        long chunkSize = 1024 * 1024; // 设置每个块的大小，根据需要进行调整

        // 计算文件总大小
        long totalSize = file.length();
        long offset = 0;

        while (offset < totalSize) {
            // 计算当前块的大小
            long chunkBytes = Math.min(chunkSize, totalSize - offset);

            // 读取当前块的数据
            byte[] chunkData = FileUtil.readBytes(file/*, offset, (int) chunkBytes*/);

            // 发送当前块
            HttpResponse response = HttpRequest.post(uploadUrl)
                    .form("fileField", file.getName(), chunkData) // "fileField" 是上传表单字段名称
                    .execute();

            // 处理上传响应
            if (response.isOk()) {
                System.out.println("Chunk uploaded successfully.");
            } else {
                System.err.println("Chunk upload failed.");
                break;
            }

            // 更新偏移量以处理下一个块
            offset += chunkBytes;
        }

        System.out.println("File upload completed.");
    }

    @Test
    public void test10() throws IOException {
        String uploadUrl = "http://10.10.113.122:8001/filecenter/uploadFile/1.2.3/playStore"; // 替换为实际的上传目标URL
        String filePath = "D:/SDP-v3.1.2-3.1.4.001-centos.tar.gz"; // 要上传的大文件的路径

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File does not exist.");
            return;
        }

        HttpURLConnection connection = null;
        DataOutputStream dos = null;
        FileInputStream fis = null;

        try {
            // 创建URL对象
            URL url = new URL(uploadUrl);

            // 打开连接
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(4096); // 4096字节块大小，根据需要进行调整


            // 设置请求方法为POST
            connection.setRequestMethod("POST");

            // 允许输入流写入
            connection.setDoInput(true);

            // 允许输出流读取
            connection.setDoOutput(true);

            // 禁用缓存
            connection.setUseCaches(false);

            // 设置HTTP请求头（根据需要）
            connection.setRequestProperty("Content-Type", "application/octet-stream");

            // 设置其他自定义请求头（根据需要）
            // connection.setRequestProperty("Custom-Header", "header-value");

            // 打开输出流到服务器
            dos = new DataOutputStream(connection.getOutputStream());

            // 从文件读取数据并写入输出流
            fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            // 刷新输出流
            dos.flush();

            // 获取HTTP响应码
            int responseCode = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            // 处理响应（根据需要）

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和流
            if (dos != null) {
                dos.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    @Test
    public void uploadTest2() {
        //客户端
        String url = "http://10.10.113.122:8001/filecenter/uploadFile";

//        Map<String, Object> params = new HashMap<>(16);
//        params.put("fileInfo", JSONUtil.toJsonStr(FileInfo.builder().version(RandomUtil.randomNumbers(6)).centerType("playStore").name("ensbrain-plus-server-admin-2.2.01.zip").build()));
//        params.put("file", new File("D:/ensbrain-plus-server-admin-2.2.01.zip"));
        Map<String, Object> params = Map.of(
                "file", new File("D:/ensbrain-plus-server-admin-2.2.01.zip"),
                "fileInfo", JSONUtil.toJsonStr(FileInfo.builder().version(RandomUtil.randomNumbers(6)).centerType("playStore").name("ensbrain-plus-server-admin-2.2.01.zip").build())
        );
        HttpRequest httpRequest = HttpRequest.post(url)
                .setChunkedStreamingMode(1024 * 1024)
                .form(params);
        String result = httpRequest.execute().body();
        log.info("result :{}", result);
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


    @Test
    public void test02() {

    }


    @Test
    public void test03() {
        File file = new File("D:\\1.png");
        String str = JSONUtil.toJsonStr(FileInfo.builder().id("123").centerType("enclient").build());


        HttpRequest request = HttpRequest.post("http://localhost:8001/filecenter/upload1")
                .contentType(ContentType.MULTIPART.toString(StandardCharsets.UTF_8))
                .form("file", file)
                .form("json", str);

        try (HttpResponse httpResponse = request.execute()) {
            String body = httpResponse.body();
            log.info("{}", body);
        }
    }


    @Test
    public void test04() {
        RestTemplate restTemplate = new RestTemplate();

        File file = new File("D:\\1.png");
        String str = JSONUtil.toJsonStr(FileInfo.builder().id("123").centerType("enclient").build());

        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", new FileSystemResource(file));
        requestBody.add("json", FileInfo.builder().id("123").centerType("enclient").build());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8001/filecenter/upload1", HttpMethod.POST, requestEntity, String.class);

    }

    @Test
    public void test06() {
        HttpClient client = HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create(""))
                .setHeader("Cookie", "")
                .build();
        client.sendAsync(request, java.net.http.HttpResponse.BodyHandlers.ofString())
                .thenApply(java.net.http.HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }


    @Test
    public void test07() {
        // 创建 HttpClient 实例
        HttpClient httpClient = HttpClient.newHttpClient();

        // 创建 HTTP 请求
        java.net.http.HttpRequest httpRequest = java.net.http.HttpRequest.newBuilder()
                .uri(URI.create("https://www.example.com"))
                .GET()
                .build();

        try {
            // 发起 HTTP 请求并获取响应
            java.net.http.HttpResponse<String> httpResponse = httpClient.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofString());

            // 处理响应
            int statusCode = httpResponse.statusCode();
            String responseBody = httpResponse.body();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body: " + responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test08() {
        URI uri = URI.create("https://www.baidu.com?name=lmc&pass=111");
        System.out.println(uri.getQuery());
        System.out.println(uri.getRawQuery());
        System.out.println(uri.getHost());
        System.out.println(uri.getAuthority());
        System.out.println(uri.getRawAuthority());

        URI uri1 = URI.create("https:/www.bai");
        System.out.println(uri1.getHost());
    }


    @Test
    public void test09() {
        HttpResponse httpResponse = HttpRequest.post("http://192.168.100.34:9092/uploadfile/")
                .form("file", new File("/C:/Users/Administrator/Desktop/202409091535311833046564310196226000.txt"))
                .timeout(30000)
                .execute();
        System.out.println(httpResponse.isOk());
        System.out.println(httpResponse.body());
    }



    @Test
    public void test11() {
        String response = HttpUtil.post("https://114.213.149.100:15443/app/auth/v1/authentication", "{\n" +
                "    \"code\": \"zhanapp\",\n" +
                "    \"authCode\": \"NWVjNDJkM2ZjMDA5NDE5MTk3Y2RhZTk0YmUyZTNjMzc=\",\n" +
                "    \"signature\": \"\",\n" +
                "    \"signAlgorithm\": \"\"\n" +
                "}");
        System.out.println(response);
    }
}
