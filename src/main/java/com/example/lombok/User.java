package com.example.lombok;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.val;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author 吕茂陈
 * @version 创建时间：2019.12.4 17:01
 */
@Data
@Builder
@Log
public class User {

    /**
     * @Data 只对成员变量起作用，
     */
    static String s = "";
    /**
     * 由于是final修饰的成员变量，不可更改，只会生成get，不会有set
     */
    final int id2;
    @NonNull
    private Integer id;
    private String userName;
    private String password;
    private String phone;

    /**
     * 不希望生成它的get/set方法
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String email;

    public static void main(String[] args) throws IOException {
        User user = User.builder().id(1).password("password").phone("13312341234").userName("username").build();
        val map = new HashMap<String, String>();
        System.out.println(user);
        user.test("ssfsdfj");
//        @Cleanup InputStream in = new FileInputStream("filepath");
//        @Cleanup OutputStream out = new FileOutputStream("path2");
//        byte[] b = new byte[1000];
//        while (true) {
//            int r = in.read(b);
//            if (r == -1) {
//                break;
//            }
//            out.write(b, 0, r);
//        }

    }

    public void test(@NonNull String s) {
        log.info("test");
        System.out.println(s);
    }
}
