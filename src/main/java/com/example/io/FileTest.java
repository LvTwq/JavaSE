package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

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
        File newFile = new File(System.currentTimeMillis() + "");
        System.out.print("==============newFile对象所对应的文件或目录==============");
        log.info(newFile.exists() ? "存在" : "不存在");

        // 以指定newFile对象创建一个文件
        log.info(newFile.createNewFile() ? "成功" : "失败");
        log.info(newFile.mkdir() ? "可以" : "无法" + "创建该目录");

        // 使用list() 方法列出当前路径下所有的文件和路径
        String[] fileList = file.list();
        log.info("====当前路径下所有的文件和路径如下====");
        for (String fileName :
                fileList) {
            log.info(fileName);
        }

        File[] roots = File.listRoots();
        log.info("====系统下所有根路径如下====");
        for (File root :
                roots) {
            log.info(String.valueOf(root));
        }
    }


    @Test
    public void contextLoads() throws Exception {
        String dir = String.join("/", "spFilePath", "9496b636d1064a33bec8b2a883a9be25");
        String tar = String.join("/", "spFilePath", "9496b636d1064a33bec8b2a883a9be25");
        zipFileTree(new File(dir), "zip");
    }

    public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
        Path file = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(file))) {
            Path path = Paths.get(sourceDirPath);
            Files.walk(path)
                    .filter(p -> !Files.isDirectory(p))
                    .forEach(p -> {
                        ZipEntry zipEntry = new ZipEntry(path.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(p, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
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
}
