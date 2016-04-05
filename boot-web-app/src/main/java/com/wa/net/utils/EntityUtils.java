package com.wa.net.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupd on 16-3-26.
 **/
public class EntityUtils {

    public static <T extends Object> T copyProperties(Class<T> vo, Object orig) {
        T v = null;
        try {
            v = vo.newInstance();
            if(orig != null){
                java.util.Date defaultValue = null;
                Converter converter = new DateConverter(defaultValue);
                BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
                beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
                BeanUtils.copyProperties(v, orig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public static  <T extends Object> List<T> copyListProperties(Class<T> vo, List<? extends Object> orig) {
        List<T> list = new ArrayList<T>();
        if (orig != null) {
            for (Object model : orig) {
                list.add(copyProperties(vo, model));
            }
        }
        return list;
    }



}
