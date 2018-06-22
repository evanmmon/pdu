package com.chuangkou.pdu.util;

import com.chuangkou.pdu.controller.JobTask;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Author:
 * @Description:
 * @Date:Created in 10:05 2018/4/28
 */
public class PropertiesUtils {
    /**
     * 根据key读取value（第一种方式 ）
     *
     * @param filePath
     *            相对路径， properties文件需在classpath目录下，
     *            比如：configure.properties在包com.test.configure下，
     *            路径就是com/test/configure/configure.properties
     * @param key
     * @return
     */
    public static String getProperty(String filePath, String key)
    {
        String value = null;
        java.util.Properties props;
        try
        {
            props = PropertiesLoaderUtils.loadAllProperties(filePath);
            value = props.getProperty(key);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return value;

    }

    /**
     * 读取properties的全部信息（第一种方式 ）
     *
     * @param filePath
     *            相对路径， properties文件需在classpath目录下，
     *            比如：configure.properties在包com.test.configure下，
     *            路径就是com/test/configure/configure.properties
     */
    public static void getProperties(String filePath)
    {
        java.util.Properties props;
        try
        {
            props = PropertiesLoaderUtils.loadAllProperties(filePath);
            printAllProperty(props);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 根据key读取value（第二种方式 ）
     *
     * @param filePath
     *            绝对路径或相对路径， 如果是相对路径，则从当前项目下的目录开始计算，
     *            如：当前项目/configure/configure.properties,
     *            相对路径就是configure/configure.properties
     * @param key
     * @return
     */
    public static String readValue(String filePath, String key)
    {
        Properties props = new Properties();
        String value = null;
        try
        {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            value = props.getProperty(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 读取properties的全部信息（第二种方式 ）
     *
     * @param filePath
     *            绝对路径或相对路径， 如果是相对路径，则从当前项目下的目录开始计算，
     *            如：当前项目/configure/configure.properties,
     *            相对路径就是configure/configure.properties
     */
    public static void readProperties(String filePath)
    {
        Properties props = new Properties();
        try
        {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            printAllProperty(props);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 根据key读取value（第三种方式 ）
     *
     * @param filePath
     *            相对路径， properties文件需在classpath目录下，
     *            比如：configure.properties在包com.test.configure下，
     *            路径就是/com/test/configure/configure.properties
     * @param key
     * @return
     */
    public static String getValue(String filePath, String key)
    {
        Properties props = new Properties();
        try
        {
            //
            InputStream in = Object.class.getResourceAsStream(filePath);
            props.load(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return props.getProperty(key);
    }

    /**
     * 读取properties的全部信息（第三种方式 ）
     *
     * @param filePath
     *            相对路径， properties文件需在classpath目录下，
     *            比如：configure.properties在包com.test.configure下，
     *            路径就是/com/test/configure/configure.properties
     * @return
     */
    public static void getValues(String filePath)
    {
        Properties props = new Properties();
        try
        {
            InputStream in = Object.class.getResourceAsStream(filePath);
            props.load(in);
            printAllProperty(props);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void printAllProperty(java.util.Properties props)
    {
        @SuppressWarnings("rawtypes")
        Enumeration en = props.propertyNames();
        while (en.hasMoreElements())
        {
            String key = (String) en.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + value);
        }
    }


    public static String loaderGetValues(String relativePath, String key) throws IOException {
        String value = "";
        try {
//            ClassLoader loader = PropertiesUtils.class.getClassLoader();
//            URL u = loader.getResource(relativePath);
//            File file = new File(u.getPath());
//
//            FileInputStream fis = new FileInputStream(file);
            Properties props = new Properties();
//            props.load(fis);
            props.load(new InputStreamReader(PropertiesUtils.class.getResourceAsStream(relativePath), "UTF-8"));
            value = props.getProperty(key);

//            System.out.println("资源文件的值为：" + value);
//            value = new String(value.getBytes("GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}
