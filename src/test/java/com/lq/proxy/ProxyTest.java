package com.lq.proxy;

import com.lq.mybatis.io.ClassLoaderWraper;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

/**
 * @program: wheel
 * @Date: 2019/8/7 0007 10:05
 * @Author: lin huan
 * @Description:
 */
public class ProxyTest {

    @Test
    void testArrayClone(){
        Object date = new Object();
        Object[] dates = new Object[2];
        dates[0] = date;
        Object[] clone = dates.clone();
        System.out.println(dates[0]);
        System.out.println(clone[0]);
        System.out.println(clone);
        System.out.println(dates);
    }
}
