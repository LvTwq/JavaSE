package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/10 14:56
 */
@Slf4j
public class ZipUtils {

    public static void main(String[] args) throws IOException {
        String dir = String.join("/", "spFilePath", "9496b636d1064a33bec8b2a883a9be25");
        zipIt(dir + "/" + "会议视频.zip", dir);

    }

    @Test
    public void test() throws IOException {
        String dir = String.join("/", "spFilePath", "9496b636d1064a33bec8b2a883a9be25");
        // 删除文件夹
        FileUtils.deleteDirectory(new File(dir));
        log.info("【{}】删除成功", dir);
    }

    /**
     * 按照期望的路径压缩文件夹
     *
     * @param zipFile      最终结果的文件名，要带 .zip
     * @param sourceFolder 路径，若按路径不存在，不会新建文件夹，但会生成zip文件
     */
    public static void zipIt(String zipFile, String sourceFolder) {
        // 文件夹内的文件
        List<String> fileList = new ArrayList<>();
        generateFileList(new File(sourceFolder), sourceFolder, fileList);

        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            log.info("【{}】添加到zip中", zipFile);
            for (String file : fileList) {
                log.info("添加文件:{}", file);
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);
                try (FileInputStream in = new FileInputStream(sourceFolder + File.separator + file)) {
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                }
            }

            zos.closeEntry();
            log.info("【{}】压缩成功", zipFile);
        } catch (IOException e) {
            log.error("压缩【{}】失败", zipFile, e);
        }
    }


    public static void generateFileList(File node, String sourceFolder, List<String> fileList) {
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString(), sourceFolder));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename), sourceFolder, fileList);
            }
        }
    }

    private static String generateZipEntry(String file, String sourceFolder) {
        return file.substring(sourceFolder.length() + 1);
    }
}
