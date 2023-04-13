package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> onlineUsers;

    private final static String KEY = "ONLINE_USERS";

    private double getTs() {
        return new Date().getTime() / 1000;
    }

    // Пример вывода всех ключей
    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    // Фиксирует посещение пользователем страницы
    void logPageVisit(int user_id)
    {
        //ZADD ONLINE_USERS
        onlineUsers.add(getTs(), String.valueOf(user_id));
    }

    // Удаляет
    void deleteOldEntries(int secondsAgo)
    {
        //ZREVRANGEBYSCORE ONLINE_USERS 0 <time_5_seconds_ago>
        onlineUsers.removeRangeByScore(0, true, getTs() - secondsAgo, true);


    }
    int calculateUsersNumber()
    {
        //ZCOUNT ONLINE_USERS
        return onlineUsers.count(Double.NEGATIVE_INFINITY, true, Double.POSITIVE_INFINITY, true);
    }

    String showNextUserOnMainPage() {
        onlineUsers = redisson.getScoredSortedSet(KEY);
        String user = onlineUsers.pollFirst();
        String result = String.format("На главной странице показываем пользователя [%s]:", user);
        onlineUsers.add(getTs(), user);
        return result;
    }

    String showPayedUserOnMainPage() {
        onlineUsers = redisson.getScoredSortedSet(KEY);
        //случайным образом выбирается порядковый номер пользователя находящегося в сете
        int position = new Random().nextInt(calculateUsersNumber());
        Collection<String> users = onlineUsers.valueRange((int)Double.NEGATIVE_INFINITY, (int) Double.POSITIVE_INFINITY);
        String payingUser = (String) users.toArray()[position];
        out.println(String.format("Пользователь %s оплатил платную услугу", payingUser));
        String result = String.format("На главной странице показываем пользователя: [%s]", payingUser);
        onlineUsers.add(getTs(), payingUser);
        return result;
    }


}
