package com.example.io;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

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
}
