package com.github.hcsp.polymorphism;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class User {
    /**
     * 用户ID，数据库主键，全局唯一
     */
    private final Integer id;

    /**
     * 用户名
     */
    private final String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    /*若一个方法满足
        把一个东西映射成bool值 输入是个对象 输出是个bool值
        则其可转换成抽象的函数接口Predicate

        此处使用Predicate函数接口，因为提取出来的条件参数满足 输入一个对象 输出bool值*/

    // 过滤姓张的用户
    private static boolean isZhang(User user) {
        return user.name.startsWith("张");
    }

    public static List<User> filterZhangUsers(List<User> users) {
        /*方法引用，使用一个类名 + 静态方法的名字
         * 把一个符合这个函数接口的静态方法 自动转换成函数接口
         * 该方法有名字，可以用其解释他在做什么
         * 可以写多行，写多行时不用lamb 用方法引用*/
        return filter(users, User::isZhang);
    }

    // 过滤姓王的用户
    public static List<User> filterWangUsers(List<User> users) {
        /*lamb表达式 user -> user.name.startsWith("王")
         * 非常短小的方法
         * (User user）方法列表 ，只有一个参数 且其类型可以推动出来 —— user
         * user.name.startsWith("王") 方法体，多句{user.name.startsWith("王");return }*/
        return filter(users, user -> user.name.startsWith("王"));
    }
    // 你可以发现，在上面三个函数中包含大量的重复代码。
    // 请尝试通过Predicate接口将上述代码抽取成一个公用的过滤器函数
    // 并简化上面三个函数


    public static List<User> filter(List<User> users, Predicate<User> predicate) {
        List<User> results = new ArrayList<>();
        for (User user : users) {
            if (predicate.test(user)) {
                results.add(user);
            }
        }
        return results;
    }
}
