package com.lq.mybatis.io;

import java.io.InputStream;
import java.net.URL;

/**
 * @program: repet_wheel
 * @Date: 2019/7/18 0018 14:48
 * @Author: lin huan
 * @Description: 来源于mybatis项目的ClassLoader包装类
 * 一个类来封装对多个类加载器的访问，使它们作为一个加载器工作
 */
public class ClassLoaderWraper {
    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    ClassLoaderWraper(){
        try {
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException e) {
            //谷歌应用程序引擎上的AccessControlException
        }
    }

    /**
     * 尝试从一个类路径上获取一个资源
     * @param resource
     * @return
     */
    public URL getResourceAsURL(String resource) {
      return getResourceAsURL(resource,getClassLoader(null));
    }

    /**
     * 尝试从一个类路径上获取一个资源，从特定的类加载器开始
     * @param resource
     * @param classLoader
     * @return
     */
    public URL getResourceAsURL(String resource, ClassLoader classLoader){
        return getResourceAsURL(resource,getClassLoader(classLoader));
    }

    /**
     * 尝试从一个类路径上获取一个资源的输入流
     * @param resource
     * @return
     */
    public InputStream getResourceAsStream(String resource) {
        return getResourceAsStream(resource,getClassLoader(null));
    }

    /**
     * 尝试从一个类路径上获取一个资源的输入流，从特定的类加载器开始
     * @param resource
     * @param classLoader
     * @return
     */
    public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
        return getResourceAsStream(resource,getClassLoader(classLoader));
    }

    /**
     * 尝试从一个类加载器路径上加载一个类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> classForName(String name) throws ClassNotFoundException {
        return classForName(name,getClassLoader(null));
    }

    /**
     * 尝试从一个类加载器路径上加载一个类，从特定的类加载器开始
     * @param name
     * @param classLoader
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> classForName(String name, ClassLoader classLoader) throws ClassNotFoundException {
        return classForName(name,getClassLoader(classLoader));
    }

    /**
     * 从一组类加载器中获取资源url
     * @param resource 资源
     * @param classLoaders 类加载器数组
     * @return
     */
    URL getResourceAsURL(String resource,ClassLoader[] classLoaders){
        URL url;
        for (ClassLoader loader : classLoaders) {
            if (loader != null){
                System.out.println(loader.getClass().toString());
                url = loader.getResource(resource);
                if (url==null){
                    url = loader.getResource("/"+resource);
                }
                if (url != null){
                    return url;
                }
            }
        }
        return null;
    }

    /**
     * 从一组类加载器中获取资源输入流
     * @param resource 资源
     * @param classLoaders 类加载器数组
     * @return
     */
    InputStream getResourceAsStream(String resource, ClassLoader[] classLoaders){
        InputStream in;
        for (ClassLoader loader : classLoaders) {
            if (loader != null) {
                in  = loader.getResourceAsStream(resource);
                if (in == null) {
                    in = loader.getResourceAsStream("/" + resource);
                }
                if (in != null){
                    return in;
                }
            }
        }
        return null;
    }

    ClassLoader[] getClassLoader(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader};
    }

    /**
     * 尝试从一组类加载器中加载类
     * @param name 类名
     * @param classLoaders 类加载器
     * @return
     * @throws ClassNotFoundException
     */
    Class<?> classForName(String name,ClassLoader[] classLoaders) throws ClassNotFoundException {
        Class clazz;
        for (ClassLoader loader : classLoaders) {
            try {
                if (loader != null){
                    clazz = Class.forName(name,true,loader);
                    if (clazz != null){
                        return clazz;
                    }
                }
            } catch (ClassNotFoundException e) {

            }
        }
        throw new ClassNotFoundException("未找到类："+name);
    }
}
