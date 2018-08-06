package com.ngsky.im.util;

import java.util.UUID;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/5/2018 11:30 PM
 **/
public class UUIDUtil {
    public static String generateUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
