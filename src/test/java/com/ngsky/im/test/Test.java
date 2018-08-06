package com.ngsky.im.test;

import com.ngsky.im.util.MD5Util;
import io.netty.util.CharsetUtil;

/**
 * @Description TODO
 * @Author daxiong
 * @Date 8/4/2018 8:01 PM
 **/
public class Test {
    public static void main(String[] args){
        String str = new String("daxiong");
        String str2 = "daxiong";
        System.out.println(new String(MD5Util.md5(str), CharsetUtil.UTF_8));
        System.out.println(new String(MD5Util.md5(str2), CharsetUtil.UTF_8));
    }
}
