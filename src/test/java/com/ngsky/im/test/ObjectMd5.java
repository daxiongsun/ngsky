package com.ngsky.im.test;

import com.ngsky.im.util.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * <dl>
 * <dt>ObjectMd5</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 8/19/2018 10:51 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class ObjectMd5 {
    public static void main(String[] args){
        Map map = new HashMap();
        map.put("daxiong", "daxiong");
        map.put("key", "key");
        System.out.println(map.toString());
        map.put("hello", "hello");
        System.out.println(map.toString());
    }
}
