package com.ngsky.im.demo.config;

/**
 * @Description 文本消息服务配置，static变量声明，可被配置文件覆盖
 * @Author daxiong
 * @Date 8/2/2018 11:25 PM
 **/
public class TextServerConfig {

    public static String TEXT_SERVER_ADDR = "127.0.0.1";
    public static int TEXT_SERVER_PORT = 9090;
    public static int BOSS_THREAD_SIZE = 100;
    public static int WORK_THREAD_SIZE = 100;

}
