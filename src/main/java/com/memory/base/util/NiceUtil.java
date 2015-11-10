package com.memory.base.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * nice ~
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 19:26
 */
public class NiceUtil {

    // TODO
    private static long autoIncrease = 1;

    /**
     * get a number increased
     */
    public static long getIncreaseLong() {
        return autoIncrease++;
    }

    /**
     * dissect object
     */
    public static void dissect(Object obj) {
        if (obj == null) {
            System.out.println("obj : null");
            return;
        }
        if (obj instanceof List) {
            System.out.println("list : ");
            for (Object e : (List) obj) {
                dissect(e);
            }
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println(field.getName() + " : " + field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * convert map to object
     */
    public static <T> T map2obj(Map<String, Object> map, Class<T> clazz) throws Exception {
        if (map == null || clazz == null) {
            return null;
        }
        T t = clazz.newInstance();
        if (map.isEmpty()) {
            return t;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            String fieldName = descriptor.getName();
            if (!fieldName.equals("class") && map.containsKey(fieldName)) {
                Method setter = descriptor.getWriteMethod();
                setter.invoke(t, map.get(fieldName));
            }
        }
        return t;
    }

    /**
     * convert object to map
     */
    public static Map<String, Object> obj2map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * return true if string given is empty, otherwise false
     */
    public static boolean isEmptyString(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * return project root path (not web project)
     */
    public static String getRootPath() {
        return new File("").getAbsolutePath() + System.getProperty("file.separator");
    }

    /**
     * return true when local OS is windows, otherwise false
     */
    public static boolean isWindowsOS() {
        return System.getProperty("file.separator").equals("\\");
    }

}
