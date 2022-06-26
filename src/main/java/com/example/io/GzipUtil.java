package com.example.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import org.junit.Test;

import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/04/25 11:41
 */
@Slf4j
public class GzipUtil {

    @Test
    public void decompress() throws IOException {
        String str = "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";
//        String str = "H4sIAAAAAAAAAHOvyix42tcNR8/3rERGz7Z2v1g/FReJHyUCQRIIJIMBfm4KGBgSBwDGzrxFtQAAAA==";
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1));
             GZIPInputStream gis = new GZIPInputStream(byteArrayInputStream);
             BufferedReader bf = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8))) {
            StringBuilder outStr = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null) {
                outStr.append(line);
            }
            log.info("{}", outStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() throws IOException {
        String str = "H4sIAAAAAAAAAHOvyix42tcNR8/3rERGz7Z2v1g/FReJHyUCQRIIJIMBfm4KGBgSBwDGzrxFtQAAAA==";
        String s = ZipUtil.unGzip(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1.name());
        log.info("{}",s);
    }

    public String decompress(final byte[] compressed) throws IOException {
        final StringBuilder outStr = new StringBuilder();
        if ((compressed == null) || (compressed.length == 0)) {
            return "";
        }
        if (isCompressed(compressed)) {
            final GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outStr.append(line);
            }
        } else {
            outStr.append(compressed);
        }
        return outStr.toString();
    }

    public boolean isCompressed(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }

}
