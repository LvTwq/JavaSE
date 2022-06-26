package com.example.guava;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 吕茂陈
 * @date 2022/04/18 17:27
 */
@Slf4j
public class RateTest {

    @Test
    public void test01(){
        RateLimiter limiter = RateLimiter.create(5);
        limiter.acquire();
        limiter.acquire();
        limiter.acquire();
        limiter.acquire();
        limiter.acquire();
        limiter.acquire();
        limiter.acquire();
        limiter.acquire();
    }
}
