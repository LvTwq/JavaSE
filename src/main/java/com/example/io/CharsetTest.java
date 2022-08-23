package com.example.io;

import cn.hutool.core.util.HexUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 吕茂陈
 * @date 2022/03/23 09:24
 */
@Slf4j
public class CharsetTest {

    private static final String WJ = "spFilePath/demo.txt";

    @Test
    public void test01() throws IOException {
        Files.deleteIfExists(Paths.get(WJ));
        // 指定以GBK格式写入，GBK 编码的汉字，每一个汉字两个字节
        Files.write(Paths.get(WJ), "你好hello".getBytes(Charset.forName("GBK")));
        log.info("{}", HexUtil.encodeHexStr(Files.readAllBytes(Paths.get(WJ))));
    }

    @Test
    public void test03() throws IOException {
        log.info("当前系统的字符集：{}", Charset.defaultCharset());
        // 指定以UTF_8格式写入，UTF_8 编码的汉字，每一个汉字三个字节
        for (int i = 0; i < 200000; i++) {
            // todo 循环写入20w行数据
            Files.write(Paths.get(WJ), "你好hello".getBytes(StandardCharsets.UTF_8));
        }
        Files.write(Paths.get(WJ), "你好hello".getBytes(StandardCharsets.UTF_8));
        log.info("bytes：{}", HexUtil.encodeHexStr(Files.readAllBytes(Paths.get(WJ))));
    }


    @Test
    public void test02() throws IOException {
        char[] chars = new char[10];
        String content = "";
        // FileReader 是以当前机器的默认字符集来读取文件的
        try (FileReader fileReader = new FileReader(WJ)) {
            int count;
            while ((count = fileReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        // 如果是GBK写入的，当前系统默认UTF_8，使用 FileReader 读取到的是乱码
        log.info("{}", content);
    }


    @Test
    public void test04() throws IOException {
        char[] chars = new char[10];
        String content = "";
        // 如果希望指定字符集的话，需要直接使用 InputStreamReader 和 FileInputStream
        try (FileInputStream fileInputStream = new FileInputStream(WJ);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("GBK"))) {
            int count;
            while ((count = inputStreamReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        // 正确输出
        log.info("{}", content);
        // 或者这样读取，但这样有个问题，readAllLines 读取文件所有内容后，放到 List<String> 中，可能会OOM
        String result = Files.readAllLines(Paths.get(WJ), Charset.forName("GBK")).stream().findAny().orElse("");
        log.info("{}", result);
    }


    @Test
    public void test05() throws IOException {
        log.info("文件大小：{}", Files.size(Paths.get(WJ)));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("read 200000 lines");
        // 使用Files.lines方法读取20万行数据
        int size = Files.lines(Paths.get(WJ)).limit(200000).collect(Collectors.toList()).size();
        log.info("lines {}", size);
        stopWatch.stop();

        stopWatch.start("read 2000000 lines");
        //使用Files.lines方法读取200万行数据
        log.info("lines {}", Files.lines(Paths.get(WJ)).limit(2000000).collect(Collectors.toList()).size());

        stopWatch.stop();
    }

    @Test
    public void test06() throws IOException {
        // 写10行数据
        Files.write(Paths.get(WJ), IntStream.rangeClosed(1, 10).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList())
                , StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        // 读100万次，每读一行计数器+1
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try {
                Files.lines(Paths.get(WJ)).forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // linux 下会 Too many open files
        log.info("总计：{}", longAdder.longValue());
    }


    @Test
    public void test07() throws IOException {
        // 写100w行数据，约35M
        Files.write(Paths.get(WJ), IntStream.rangeClosed(1, 1000000).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList())
                , StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        // 把原文件写入目标文件————每读取一个字节、每写入一个字节，都会进行一次IO操作，代价太大了
        try (FileInputStream fileInputStream = new FileInputStream(WJ);
             FileOutputStream fileOutputStream = new FileOutputStream("spFilePath/target.txt")) {
            int i;
            while ((i = fileInputStream.read()) != -1) {
                fileOutputStream.write(i);
            }
        }
    }


    /**
     * 使用1000字节缓冲区作为过度，可以提高性能
     *
     * @throws IOException
     */
    @Test
    public void test08() throws IOException {
        StopWatch stopWatch = new StopWatch();

        /*
        虽然使用了缓冲流，但逐字节的操作因为方法调用次数实在太多还是慢
         */
        stopWatch.start("使用BufferedInputStream和BufferedOutputStream");
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(WJ));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("spFilePath/target.txt"))) {
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        }
        stopWatch.stop();

        stopWatch.start("额外使用一个8KB缓冲，再使用BufferedInputStream和BufferedOutputStream");
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(WJ));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("spFilePath/target.txt"))) {
            byte[] buffer = new byte[8192];
            int len = 0;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, len);
            }
        }
        stopWatch.stop();

        stopWatch.start("直接使用FileInputStream和FileOutputStream，再使用一个8KB的缓冲");
        try (FileInputStream fileInputStream = new FileInputStream(WJ);
             FileOutputStream fileOutputStream = new FileOutputStream("spFilePath/target.txt")) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());
    }
}
