package com.lm.lmliving.seckill.config;

import com.lm.lmliving.seckill.pojo.User;

/**
 * @author lm
 * @version 1.0
 */
public class UserContext {

    //每个线程都有自己的ThreadLocal, 把共享数据存放到这里，保证线程安全
    private static ThreadLocal<User> userHolder =
            new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }
}
