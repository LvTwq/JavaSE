package com.example.regular;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 正则学习
 *
 * @author 吕茂陈
 */
@Slf4j
public class RegularTest {


    @Test
    public void test13() {
        List<String> forwarderNames = Arrays.stream("jai,sdgi，jspdr".split("[,，]"))
                .map(String::trim).collect(Collectors.toList());
        System.out.println(forwarderNames);
    }


    @Test
    public void test12() {
        System.out.println(Validator.isUrl("http://www.baidu.com"));
        System.out.println(Validator.isUrl("http://www,baidu.com"));
    }


    private static final Pattern ExpPattern = Pattern.compile("\\[(.*?)]");


    @Test
    public void test08() {
        String url = "url={system:[self=ajsl?ajslbl:zjjdApi]}/jdgl/blfk/blfklb?systemid={self}";
        Matcher m = ExpPattern.matcher(url);
        while (m.find()) {
            // group是针对（）来说的，group（0）就是指的整个串，group（1） 指的是第一个括号里的东西
            String key0 = m.group();
            log.info("{}", key0);
            String key1 = m.group(1);
            log.info("{}", key1);
        }
    }

    @Test
    public void test01() {
        String str = "hello , java!";
        log.info("{}", str.replaceFirst("\\w*", "@"));
        log.info("{}", str.replaceAll("\\w*", "@"));
        log.info("{}", str.replaceFirst("\\w*?", "@"));
        log.info("{}", str.replaceAll("\\w*?", "@"));
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
        log.info("{}", "数组长度：" + split2.length);
        for (String s : split2) {
            log.info(s + "\t");
        }
        log.info("{}", "\n ====================");
        String[] split1 = string.split("\\?", -1);
        log.info("{}", "数组长度：" + split1.length);
        for (String s : split1) {
            log.info(s + "\t");
        }
    }


    private static final Pattern test03 = Pattern.compile("a*b");

    @Test
    public void test03() {
        /*
        Pattern 对象是正则表达式编译后在内存中的表示形式，因此，
        正则表达式字符串必须先被编译为Pattern对象，再利用该Pattern对象创建对应的Matcher对象
        执行匹配所涉及的状态保留在Matcher对象中，多个Matcher对象可共享一个Pattern对象
        */
        Matcher m = test03.matcher("aaaaab");
        boolean b = m.matches();
        log.info("{}", b);
    }


    /**
     * 匹配 13X和15X段的手机号
     */
    private static final Pattern test04 = Pattern.compile("((13)|(15))\\d{9}");

    @Test
    public void test04() {
        String str = "联系方式13500006666，联系我15899903312";
        Matcher m = test04.matcher(str);
        // 返回目标字符串中是否包含与Pattern匹配的子串
        while (m.find()) {
            // 返回上一次与Pattern匹配的子串
            log.info("{}", m.group());
        }
    }


    /**
     * 匹配所有单词字符，包括0~9所有数字，26个英文字母和下划线
     */
    private static final Pattern test05 = Pattern.compile("\\w+");

    @Test
    public void test05() {
        String str = "Java is very easy!";
        log.info("{}", "目标字符串是：" + str);
        Matcher m = test05.matcher(str);
        while (m.find()) {
            log.info("{}", m.group() + "子串起始位置：" + m.start() + "，其结束位置：" + m.end());
        }
    }


    /**
     * 匹配邮箱地址
     */
    private static final Pattern test06 = Pattern.compile("\\w{3,20}@\\w+\\.(com|org|cn|net|gov)");

    @Test
    public void test06() {
        String[] mails = {
                "kongyee@163.com",
                "kongkyu@gmail.com",
                "dlsikjg.qq"
        };
        for (String mail : mails) {
            Matcher matcher = test06.matcher(mail);
            // 将现有的Matcher对象应用于一个新的字符序列
            matcher.reset(mail);
            String result = mail + (matcher.matches() ? " 是" : " 不是") + "一个有效的邮件地址";
            log.info("{}", result);
        }
    }


    /**
     * 匹配以 re 开头的字符串
     */
    private static final Pattern test07 = Pattern.compile("re\\w*");

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
            log.info("{}", matcher.replaceAll("哈哈:)"));
        }
        log.info("{}", "\n==================");
        for (String msg : msgs) {
            log.info("{}", msg.replaceAll("re\\w*", "哈哈:)"));
            log.info("{}", msg.replaceFirst("re\\w*", "哈哈:)"));
//            log.info("{}",Arrays.toString(msg.split(" ")));
        }
    }


    @Test
    public void test09() {
        String str = "22_02_08 15:35:43.mp4";
        String replace = str.replace(":", "_");
        log.info("{}", replace);
    }

    private static final Pattern IE_VERSION = Pattern.compile("MSIE (\\d+\\.\\d+);");

    @Test
    public void test10() {
        String str = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6. 1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET C";
        // NullPointerException
//		String str = null;
        Matcher matcher = IE_VERSION.matcher(str);
        double ieVer = 0;
        while (matcher.find()) {
            // MSIE 8.0;
            log.info(matcher.group(0));
            // 8.0
            ieVer = Double.parseDouble(matcher.group(1));
        }
        log.info("{}", ieVer);
        log.info("{}", ieVer <= 10);

    }


    private static final Pattern actionPattern = Pattern.compile("ModSecurity: (.*?)\\.");
    private static final Pattern serverPattern = Pattern.compile("(server: (.*?),)");
    private static final Pattern deniedHostPattern = Pattern.compile("(host: \"(.*?)\")");
    private static final Pattern warningHostPattern = Pattern.compile("(Host: (.*?))");
    private static final Pattern paramPattern = Pattern.compile("(\\[(.*?)\\])");

    private static final Pattern requestPattern = Pattern.compile("(request: \"(.*?)\")");

    @Test
    public void test11() {
//        String all = "---38g72lJL---A--\n[03/Nov/2023:08:08:33 +0800] 169897011338.275133 10.10.72.118 49765 10.10.108.156 443\n---38g72lJL---B--\nGET /?query=test%27%20OR%20%271%27=%271 HTTP/1.1\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36 Edg/118.0.2088.76\nSec-Fetch-Site: none\nsec-ch-ua-platform: \"Windows\"\nPragma: no-cache\nUpgrade-Insecure-Requests: 1\nsec-ch-ua-mobile: ?0\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\nCache-Control: no-cache\nsec-ch-ua: \"Chromium\";v=\"118\", \"Microsoft Edge\";v=\"118\", \"Not=A?Brand\";v=\"99\"\nSec-Fetch-User: ?1\nConnection: keep-alive\nSec-Fetch-Mode: navigate\nHost: oula1.lvmc.top\nSec-Fetch-Dest: document\nAccept-Encoding: gzip, deflate, br\nCookie: BD_UPN=12314753\nAccept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\n\n\n---38g72lJL---H--\nModSecurity: Warning. detected SQLi using libinjection. [file \"/home/lvmc-plus/lvmc/appgateway/modsec/././ruleset/owasp-modsecurity-crs-3.0.2/rules/REQUEST-942-APPLICATION-ATTACK-SQLI.conf\"] [line \"43\"] [id \"942100\"] [rev \"1\"] [msg \"SQL Injection Attack Detected via libinjection\"] [data \"Matched Data: s&sos found within ARGS:query: test' OR '1'='1\"] [severity \"4\"] [ver \"OWASP_CRS/3.0.0\"] [maturity \"1\"] [accuracy \"8\"] [hostname \"10.10.108.156\"] [uri \"/\"] [unique_id \"169897011338.275133\"] [ref \"v12,15\"]\n\n---38g72lJL---Z--\n";
        String all = "---3Pl1rxyJ---A--\n" +
                "[03/Nov/2023:17:38:48 +0800] 16990043285.609005 10.10.72.118 55435 10.10.108.156 443\n" +
                "---3Pl1rxyJ---B--\n" +
                "GET /index.html?cmd=%7C%65%63%68%6F%20%27%54%65%73%74%69%6E%67%27 HTTP/1.1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36 Edg/118.0.2088.76\n" +
                "Sec-Fetch-Site: none\n" +
                "sec-ch-ua-platform: \"Windows\"\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "If-None-Match: W/\"c1d2d-30a-5b2a4a25f34ad\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "If-Modified-Since: Tue, 27 Oct 2020 10:37:32 GMT\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n" +
                "Cache-Control: max-age=0\n" +
                "sec-ch-ua: \"Chromium\";v=\"118\", \"Microsoft Edge\";v=\"118\", \"Not=A?Brand\";v=\"99\"\n" +
                "Sec-Fetch-User: ?1\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Connection: keep-alive\n" +
                "Host: oula2.lvmc.top\n" +
                "Sec-Fetch-Dest: document\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6\n" +
                "\n" +
                "\n" +
                "---3Pl1rxyJ---H--\n" +
                "ModSecurity: Warning. Matched \"Operator `Rx' with parameter `(?:;|\\{|\\||\\|\\||&|&&|\\n|\\r|\\$\\(|\\$\\(\\(|`|\\${|<\\(|>\\(|\\(\\s*\\))\\s*(?:{|\\s*\\(\\s*|\\w+=(?:[^\\s]*|\\$.*|\\$.*|<.*|>.*|\\'.*\\'|\\\".*\\\")\\s+|!\\s*|\\$)*\\s*(?:'|\\\")*(?:[\\?\\*\\[\\]\\(\\)\\-\\|+\\w'\\\"\\./\\\\\\\\]+/)?[\\\\\\\\'\\\"]*(?: (5223 characters omitted)' against variable `ARGS:cmd' (Value: `|echo 'Testing'' ) [file \"/home/lvmc-plus/lvmc/appgateway/modsec/././ruleset/owasp-modsecurity-crs-3.0.2/rules/REQUEST-932-APPLICATION-ATTACK-RCE.conf\"] [line \"56\"] [id \"932100\"] [rev \"4\"] [msg \"Remote Command Execution: Unix Command Injection\"] [data \"Matched Data: |echo 'Testing found within ARGS:cmd: |echo 'Testing'\"] [severity \"2\"] [ver \"OWASP_CRS/3.0.0\"] [maturity \"8\"] [accuracy \"8\"] [tag \"application-multi\"] [tag \"language-shell\"] [tag \"platform-unix\"] [tag \"attack-rce\"] [tag \"OWASP_CRS/WEB_ATTACK/COMMAND_INJECTION\"] [tag \"WASCTC/WASC-31\"] [tag \"OWASP_TOP_10/A1\"] [tag \"PCI/6.5.2\"] [hostname \"10.10.108.156\"] [uri \"/index.html\"] [unique_id \"16990043285.609005\"] [ref \"o0,14v20,15\"]\n" +
                "ModSecurity: Warning. Matched \"Operator `Rx' with parameter `(?i)(?:;|\\{|\\||\\|\\||&|&&|\\n|\\r|`)\\s*[\\(,@\\'\\\"\\s]*(?:[\\w'\\\"\\./]+/|[\\\\\\\\'\\\"\\^]*\\w[\\\\\\\\'\\\"\\^]*:.*\\\\\\\\|[\\^\\.\\w '\\\"/\\\\\\\\]*\\\\\\\\)?[\\\"\\^]*(?:m[\\\"\\^]*(?:y[\\\"\\^]*s[\\\"\\^]*q[\\\"\\^]*l(?:[\\\"\\^]*(?:d[\\\"\\^]*u[\\\"\\^]*m[ (4992 characters omitted)' against variable `ARGS:cmd' (Value: `|echo 'Testing'' ) [file \"/home/lvmc-plus/lvmc/appgateway/modsec/././ruleset/owasp-modsecurity-crs-3.0.2/rules/REQUEST-932-APPLICATION-ATTACK-RCE.conf\"] [line \"160\"] [id \"932110\"] [rev \"4\"] [msg \"Remote Command Execution: Windows Command Injection\"] [data \"Matched Data: |echo found within ARGS:cmd: |echo 'Testing'\"] [severity \"2\"] [ver \"OWASP_CRS/3.0.0\"] [maturity \"9\"] [accuracy \"8\"] [tag \"application-multi\"] [tag \"language-shell\"] [tag \"platform-windows\"] [tag \"attack-rce\"] [tag \"OWASP_CRS/WEB_ATTACK/COMMAND_INJECTION\"] [tag \"WASCTC/WASC-31\"] [tag \"OWASP_TOP_10/A1\"] [tag \"PCI/6.5.2\"] [hostname \"10.10.108.156\"] [uri \"/index.html\"] [unique_id \"16990043285.609005\"] [ref \"o0,5v20,15\"]\n" +
                "\n" +
                "---3Pl1rxyJ---Z--";
        JSONObject jsonObject = new JSONObject();
        String hostPort = "";
        for (String temp : StringUtils.split(all, "\n")) {

            if (StringUtils.startsWith(temp, "[")) {
                String[] split = StringUtils.split(temp, " ");
                jsonObject.set("client", split[5]);
                hostPort = split[6];
            }

            // 处理Warning的Request
            if (StringUtils.startsWith(temp, "GET ")) {
                String[] split = StringUtils.split(temp, " ");
                jsonObject.set("request", split[1]);
            }

            Matcher requestMatcher = requestPattern.matcher(temp);
            if (requestMatcher.find()) {
                String str = requestMatcher.group(1);
                String requestStr = StrUtil.removeAny(StrUtil.subAfter(str, ": ", false), "\"", "GET ", "HTTP/1.1");
                jsonObject.set("request", requestStr);
            }

            if (StringUtils.contains(temp, "Host")) {
                String host = StringUtils.remove(temp, "Host: ");
                // 说明没有端口
                if (!StringUtils.contains(host, ":") && StringUtils.isNotBlank(hostPort)) {
                    host = host + hostPort;
                }
                jsonObject.set("host", host);
            }

            if (StrUtil.contains(temp,"ModSecurity")) {
                // 匹配 ModSecurity: 和 . 之间的字符串
                String action = "";
                Matcher actionMatcher = actionPattern.matcher(temp);
                if (actionMatcher.find()) {
                    String result = actionMatcher.group(1);
                    // 执行动作
                    if (StringUtils.contains(result, "denied")) {
                        result = "denied";
                    }
                    jsonObject.set("action" , result);
                    action = result;
                }

                Matcher paramMatcher = paramPattern.matcher(temp);
                while (paramMatcher.find()) {
                    String str = paramMatcher.group(1);
                    String result = StrUtil.unWrap(str,"[","]");
                    String ttt = StrUtil.subBefore(result," ",false);
                    String ddd = StrUtil.subAfter(result," ",false);
                    if (StrUtil.isNotBlank(ddd)){
                        ddd = StrUtil.removePrefix(ddd,"\"");
                        ddd = StrUtil.removeSuffix(ddd,"\"");
                    }
                    jsonObject.set(ttt,ddd);
                }

                Matcher serverMatcher = serverPattern.matcher(temp);
                if (serverMatcher.find()){
                    String str = serverMatcher.group(1);
                    String ddd = StrUtil.subAfter(str,": ",false);
                    ddd = StrUtil.removeSuffix(ddd,",");
                    jsonObject.set("server",ddd);
                }

                Matcher deniedHostMatcher = deniedHostPattern.matcher(temp);
                if (deniedHostMatcher.find()) {
                    String str = deniedHostMatcher.group(1);
                    String host = StrUtil.removeAll(StrUtil.subAfter(str,": ",false), "\"");
                    // todo 如果是默认端口，这里不能直接拿到
                    jsonObject.set("host", host);
                }


                // 原始日志时间不好取，取当前时间
                long mills = System.currentTimeMillis();
                jsonObject.set("@mtime",mills);
//            jsonObject.set("@timestamp",tsToString(mills));

            }
        }
        jsonObject.set("111", "111");
    }

}
