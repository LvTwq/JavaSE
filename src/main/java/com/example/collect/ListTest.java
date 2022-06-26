package com.example.collect;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.example.lombok.Student;

/**
 * @author 吕茂陈
 */
public class ListTest {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add("javascript");
        books.add("python");
        books.add("java");
        System.out.println(books);

    }


    @Test
    public void test01(){
        List<Student> list = new ArrayList<>();
        list.add(null);
        System.out.println(CollectionUtils.isEmpty(list));
    }
}


