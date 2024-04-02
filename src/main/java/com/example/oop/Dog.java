package com.example.oop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * @author Administrator
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    private String name;


    public void jump(){
        System.out.println("正在执行jump()方法" );
    }

    @Test
    public void run(){
        jump();
    }
}
