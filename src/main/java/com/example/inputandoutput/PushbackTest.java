package com.example.inputandoutput;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * @author 吕茂陈
 */
public class PushbackTest {
    public static void main(String[] args) {
        try (
                //　创建PushbackReader对象，指定推回缓冲区的长度为64
                PushbackReader pr = new PushbackReader(new FileReader("src/main/java/org/example/inputandoutput/PushbackTest.java"), 64)) {
            char[] buf = new char[32];
            // 保存上次读取过的字符串内容
            String lastContent = "";
            int hasRead;
            while ((hasRead = pr.read(buf)) > 0) {
                // 将读取的内容转换成字符串
                String content = new String(buf, 0, hasRead);
                int targetIndex;
                // 将上次读取的字符串和本次读取的字符串拼起来，查看是否包含目标字符串，如果包含目标字符串
                if ((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0) {
                    // 将本次内容和上次内容一起推回缓冲区
                    pr.unread((lastContent + content).toCharArray());
                    // 重新定义一个长度为targetIndex的char数组
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }
                    // 再次读取指定长度的内容
                    pr.read(buf, 0, targetIndex);
                    System.out.println(new String(buf, 0, targetIndex));
                    // 非零表示异常终止，0表示正常终止
                    System.exit(0);
                } else {
                    System.out.println(lastContent);
                    // 将本次内容设置为上次读取的内容
                    lastContent = content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
