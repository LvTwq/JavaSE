package com.example.file;

import java.io.File;

import org.junit.Test;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/02/16 17:35
 */
@Slf4j
public class Base64Test {

    @Test
    public void encodeFile2Base64() {
//        File file = new File("spFilePath/编程匠艺01.pdf");
//        File file = new File("spFilePath/OCR对接文档(更新时间20210825).docx");
        File file = new File("spFilePath/cookie1.png");
//        File file = new File("spFilePath/身份证-邓欲国.jpg");
//        File file = new File("spFilePath/双语身份证-蒙语-田美媛.jpg");
//        File file = new File("spFilePath/身份证正反面-代用名.png");
//        File file = new File("spFilePath/双语身份证.jpg");
        String encode = Base64.encode(file);
        log.info("{}", encode);

    }
}
