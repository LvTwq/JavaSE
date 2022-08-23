package com.example.io;

import com.example.lambda.ThrowingFunction;
import com.example.optional.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 吕茂陈
 */
@Slf4j
public class FileTest {
    public static void main(String[] args) throws IOException {
        // 以当前路径创建一个File对象
        File file = new File(".");
        // 获取此File对象表示的文件名或路径名
        log.info("文件名：" + file.getName());
        // 获取相对路径的父路径
        log.info("父路径：" + file.getParent());
        // 获取绝对路径
        log.info("绝对路径:" + file.getAbsoluteFile());
        log.info("绝对路径名:" + file.getAbsolutePath());
        // 获取上一级路径
        log.info("上一级路径:" + file.getAbsoluteFile().getParent());

        // 在当前路径下创建一个临时文件
        File tmpFile = File.createTempFile("aaa", ".txt", file);
        // 指定当jvm退出时删除该文件
        tmpFile.deleteOnExit();

        // 以系统时间作为新文件名创建新文件
        File newFile = new File(System.currentTimeMillis() + ".txt");
        log.info("==============newFile对象所对应的文件或目录==============");
        log.info(newFile.exists() ? "存在" : "不存在");

        // 以指定newFile对象创建一个文件
        log.info(newFile.createNewFile() ? "成功" : "失败");
        log.info(newFile.mkdir() ? "可以" : "无法" + "创建该目录");

        // 使用list() 方法列出当前路径下所有的文件和路径
        String[] fileList = newFile.list();
        log.info("====当前路径下所有的文件和路径如下====");
        for (String fileName : fileList) {
            log.info(fileName);
        }

        File[] roots = File.listRoots();
        log.info("====系统下所有根路径如下====");
        for (File root : roots) {
            log.info(String.valueOf(root));
        }
    }


    @Test
    public void contextLoads() throws Exception {
        String dir = String.join("/", "spFilePath", "9496b636d1064a33bec8b2a883a9be25");
        String tar = String.join("/", "spFilePath", "9496b636d1064a33bec8b2a883a9be25");
        zipFileTree(new File(dir), "zip");
    }


    /**
     * 压缩文件或文件夹（包括所有子目录文件）
     *
     * @param sourceFile 源文件
     * @param format     格式（zip或rar）
     * @throws IOException 异常信息
     */
    public static void zipFileTree(File sourceFile, String format) throws IOException {
        ZipOutputStream zipOutputStream = null;
        try {
            String zipFileName;
            if (sourceFile.isDirectory()) { // 目录
                zipFileName = sourceFile.getParent() + File.separator + sourceFile.getName() + "."
                        + format;
            } else { // 单个文件
                zipFileName = sourceFile.getParent()
                        + sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."))
                        + "." + format;
            }
            // 压缩输出流
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
            zip(sourceFile, zipOutputStream, "");
        } finally {
            if (null != zipOutputStream) {
                // 关闭流
                try {
                    zipOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 递归压缩文件
     *
     * @param file            当前文件
     * @param zipOutputStream 压缩输出流
     * @param relativePath    相对路径
     * @throws IOException IO异常
     */
    private static void zip(File file, ZipOutputStream zipOutputStream, String relativePath)
            throws IOException {

        FileInputStream fileInputStream = null;
        try {
            // 当前为文件夹
            if (file.isDirectory()) {
                // 当前文件夹下的所有文件
                File[] list = file.listFiles();
                if (null != list) {
                    // 计算当前的相对路径
                    relativePath += (relativePath.length() == 0 ? "" : "/") + file.getName();
                    // 递归压缩每个文件
                    for (File f : list) {
                        zip(f, zipOutputStream, relativePath);
                    }
                }
            } else { // 压缩文件
                // 计算文件的相对路径
                relativePath += (relativePath.length() == 0 ? "" : "/") + file.getName();
                // 写入单个文件
                zipOutputStream.putNextEntry(new ZipEntry(relativePath));
                fileInputStream = new FileInputStream(file);
                int readLen;
                byte[] buffer = new byte[1024];
                while ((readLen = fileInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, readLen);
                }
                zipOutputStream.closeEntry();
            }
        } finally {
            // 关闭流
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    @Test
    public void filesWalk() throws IOException {
        // 无限深度，递归遍历文件夹
        try (Stream<Path> pathStream = Files.walk(Paths.get("."))) {
            // 只查普通文件
            pathStream.filter(Files::isRegularFile)
                    // 搜索 java 源文件
                    .filter(FileSystems.getDefault().getPathMatcher("glob:**/*.java")::matches)
                    .flatMap(ThrowingFunction.unchecked(path -> {
                        // 读取文件内容，转换为 Stream<List>
                        return Files.readAllLines(path).stream()
                                // 使用正则过滤带有 public class 的行
                                .filter(line -> Pattern.compile("public class").matcher(line).find())
                                //把这行文件内容转换为文件名+行
                                .map(line -> path.getFileName() + ">>" + line);
                    })).forEach(e -> log.info("{}", e));
        }
    }


    @Test
    public void replaceAdd() throws IOException {
        List<String> newLines = new ArrayList<>();
        Files.readAllLines(Path.of("spFilePath/dnsmasq.conf"), StandardCharsets.UTF_8)
                .forEach(line -> {
                    if (line.contains("listen-address")) {
                        newLines.add("listen-address=114.114.114.115");
                    } else {
                        newLines.add(line);
                    }
                });
        Files.write(Path.of("spFilePath/dnsmasq.conf"), newLines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Test
    public void test01() throws IOException {
        List<Employee> list = List.of(Employee.builder().name("jsduiolfg").describe("描述").build(), Employee.builder().name("asdgdsfagd").describe("描述").build());
        List<String> collect = list.stream().map(e -> e.getName() + StringUtils.SPACE + e.getDescribe()).collect(Collectors.toList());
        log.info("{}", collect);
        Files.write(Path.of("spFilePath/demo.txt"), collect, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
