package com.lq.mybatis.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @program: repet_wheel
 * @Date: 2019/7/18 0018 15:41
 * @Author: lin huan
 * @Description:
 */
public class ClassLoaderWraperTest {
    private final String RESOURCE_NOT_FOUND = "some_resource_that_does_not_exist.properties";
    private final String CLASS_NOT_FOUND = "some.random.class.that.does.not.Exist";
    private final String CLASS_FOUND = "java.lang.Object";

    private ClassLoaderWraper wraper;

    private int i;
    @BeforeEach
    public void before(){
        System.out.println("执行了");
        wraper = new ClassLoaderWraper();
        i = 2;
    }

    @Test
    public void testGetResorceAsURL(){
        URL url = wraper.getResourceAsURL("com/lq/mybatis/io/blog-derby.properties");
       // System.out.println("哈哈哈");
        System.out.println(url);
        assertNotNull(url);
    }

    @Test
    public void testi(){
        Assertions.assertEquals(2,i);
    }
}
