package com.example.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则学习
 *
 * @author 吕茂陈
 */
public class RegularTest {

    /**
     * 匹配 13X和15X段的手机号
     */
    private static final Pattern test04 = Pattern.compile("((13)|(15))\\d{9}");

    private static final Pattern test03 = Pattern.compile("a*b");

    /**
     * 匹配所有单词字符，包括0~9所有数字，26个英文字母和下划线
     */
    private static final Pattern test05 = Pattern.compile("\\w+");

    /**
     * 匹配邮箱地址
     */
    private static final Pattern test06 = Pattern.compile("\\w{3,20}@\\w+\\.(com|org|cn|net|gov)");

    /**
     * 匹配以 re 开头的字符串
     */
    private static final Pattern test07 = Pattern.compile("re\\w*");

    private static final Pattern ExpPattern = Pattern.compile("\\[(.*?)]");


    @Test
    public void test08() {
        String url = "url={system:[self=ajsl?ajslbl:zjjdApi]}/jdgl/blfk/blfklb?systemid={self}";
        Matcher m = ExpPattern.matcher(url);
        while (m.find()) {
            // group是针对（）来说的，group（0）就是指的整个串，group（1） 指的是第一个括号里的东西
            String key0 = m.group();
            System.out.println(key0);
            String key1 = m.group(1);
            System.out.println(key1);
        }
    }

    @Test
    public void test01() {
        String str = "hello , java!";
        System.out.println(str.replaceFirst("\\w*", "@"));
        System.out.println(str.replaceAll("\\w*", "@"));
        System.out.println(str.replaceFirst("\\w*?", "@"));
        System.out.println(str.replaceAll("\\w*?", "@"));
    }

    /**
     * limit 参数通过控制分割次数从而影响分割结果
     * 如果传入 n(n>0) 那么字符串最多被分割 n-1 次,分割得到数组长度最大是 n
     * 如果 n = -1 将会以最大分割次数分割
     * 如果 n = 0 将会以最大分割次数分割,但是分割结果会舍弃末位的空串
     */
    @Test
    public void rest02() {
        String string = "61a09b949376fbebb3b044e14e61617d?url={system:[self=ajsl?ajslbl:zjjdApi]}/jdgl/blfk/blfklb?systemid={self}";
        String[] split2 = string.split("\\?", 2);
        System.out.println("数组长度：" + split2.length);
        for (String s : split2) {
            System.out.print(s + "\t");
        }
        System.out.println("\n ====================");
        String[] split1 = string.split("\\?", -1);
        System.out.println("数组长度：" + split1.length);
        for (String s : split1) {
            System.out.print(s + "\t");
        }
    }


    @Test
    public void test03() {
        /*Pattern 对象是正则表达式编译后在内存中的表示形式，因此，
        正则表达式字符串必须先被编译为Pattern对象，再利用该Pattern对象创建对应的Matcher对象
        执行匹配所涉及的状态保留在Matcher对象中，多个Matcher对象可共享一个Pattern对象*/
        Matcher m = test03.matcher("aaaaab");
        boolean b = m.matches();
        System.out.println(b);
    }


    @Test
    public void test04() {
        String str = "联系方式13500006666，联系我15899903312";
        Matcher m = test04.matcher(str);
        // 返回目标字符串中是否包含与Pattern匹配的子串
        while (m.find()) {
            // 返回上一次与Pattern匹配的子串
            System.out.println(m.group());
        }
    }

    @Test
    public void test05() {
        String str = "Java is very easy!";
        System.out.println("目标字符串是：" + str);
        Matcher m = test05.matcher(str);
        while (m.find()) {
            System.out.println(m.group() + "子串起始位置：" + m.start() + "，其结束位置：" + m.end());
        }
    }

    @Test
    public void test06() {
        String[] mails = {
                "kongyee@163.com",
                "kongkyu@gmail.com",
                "dlsikjg.thunisoft"
        };
        for (String mail : mails) {
            Matcher matcher = test06.matcher(mail);
            // 将现有的Matcher对象应用于一个新的字符序列
            matcher.reset(mail);
            String result = mail + (matcher.matches() ? " 是" : " 不是") + "一个有效的邮件地址";
            System.out.println(result);
        }
    }


    @Test
    public void test07() {
        String[] msgs = {
                "java has regular",
                "now in java",
                "java expressions"
        };
        for (String s : msgs) {
            Matcher matcher = test07.matcher(s);
            matcher.reset(s);
            System.out.println(matcher.replaceAll("哈哈:)"));
        }
        System.out.println("\n==================");
        for (String msg : msgs) {
            System.out.println(msg.replaceAll("re\\w*", "哈哈:)"));
            System.out.println(msg.replaceFirst("re\\w*", "哈哈:)"));
//            System.out.println(Arrays.toString(msg.split(" ")));
        }
    }


    @Test
    public void test09(){
        String str = "22_02_08 15:35:43.mp4";
        String replace = str.replace(":", "_");
        System.out.println(replace);
    }

}
