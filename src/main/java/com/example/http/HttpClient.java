package com.example.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author 吕茂陈
 * @date 2022/02/08 16:56
 */
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
            System.out.println(string);

            byte[] bytes = body.bytes();
            System.out.println(bytes.length);

            InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            System.out.println(byteArrayInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        File tempFile = File.createTempFile("123", "png", new File("E:\\Code\\JavaSE\\src\\main\\resources"));
//            tempFile.deleteOnExit();
//            FileOutputStream out = new FileOutputStream(tempFile);
//            IOUtils.copy(inputStream, out);
    }
}
