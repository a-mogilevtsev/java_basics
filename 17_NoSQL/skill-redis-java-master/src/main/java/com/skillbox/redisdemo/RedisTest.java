package com.skillbox.redisdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1000; // 1 секунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int UsersOnline) {
        String log = String.format("[%s] Пользователей онлайн: %d", DF.format(new Date()), UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();
        for(int i = 0; i <20; i++) {
            redis.logPageVisit(i);
        }
        for(int i = 0; i < 50; i++) {
            if(i % 10 == 0) {
                out.println(redis.showPayedUserOnMainPage());
            } else {
                out.println(redis.showNextUserOnMainPage());
            }
            Thread.sleep(SLEEP);
        }
        redis.shutdown();
    }
}
