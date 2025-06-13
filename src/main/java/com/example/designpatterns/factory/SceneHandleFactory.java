package com.example.designpatterns.factory;

import cn.hutool.core.map.MapUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吕茂陈
 * @description
 * @date 2024/8/28 14:11
 */
public class SceneHandleFactory<A> implements ApplicationContextAware, InitializingBean {


    private final Map<String, SceneHandleBase> statusMap = new HashMap<>();
    private ApplicationContext applicationContext;


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, SceneHandleBase> recommendStrategyMap = applicationContext.getBeansOfType(SceneHandleBase.class);
        if (MapUtil.isEmpty(recommendStrategyMap)) {
            return;
        }
        for (SceneHandleBase strategy : recommendStrategyMap.values()) {
            SceneHandleType statusType = strategy.getClass().getAnnotation(SceneHandleType.class);
            if (null == statusType) {
                continue;
            }
            String statusValue = statusType.value();
            if (null == statusValue) {
                continue;
            }
            statusMap.put(statusValue, strategy);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
