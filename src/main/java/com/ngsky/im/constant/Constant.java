package com.ngsky.im.constant;

import com.ngsky.im.connection.Connection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description 业务相关常量
 * @Author daxiong
 * @Date 8/4/2018 4:05 PM
 **/
public class Constant {
    public final static Map<String, Connection> CONNECTION_MAP = new LinkedHashMap<>();  // 采用 LinkedHashMap 保证连接顺序
}
