package com.example.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/27 14:22
 */
@Slf4j
public class ResourceReader {


    private ResourceLoader resourceLoader;

    public ResourceReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public void readResource() {
        Resource resource = resourceLoader.getResource("classpath:updateNginx.txt");
        try (InputStream inputStream = resource.getInputStream()) {
            List<String> tmp = IOUtils.readLines(inputStream, StandardCharsets.UTF_8.name());
            log.info("{}", tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        ResourceReader reader = new ResourceReader();
//        reader.readResource();
    }


    @Test
    public void test01() {
        Resource resource = new ClassPathResource("updateNginx.txt");
        try (InputStream inputStream = resource.getInputStream()) {
            List<String> tmp = IOUtils.readLines(inputStream, StandardCharsets.UTF_8.name());
            log.info("{}", tmp);
        } catch (Exception e) {
            log.error("调用平台接口修改nginx", e);
        }
    }

}
