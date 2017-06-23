package com.stony.sso.cache.test;

import com.stony.sso.cache.annotation.Cachezable;
import org.junit.Ignore;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Stony  Date: 2016/4/7 Time: 15:07
 */
@Service
public class UserService {

    public static final AtomicInteger index = new AtomicInteger(0);

    public static final String KEY = "getUser";
    /**
     * prefixType  'getUser_' + #id
     * {@link Cachezable.PrefixType#METHOD_NAME}
     * @param id
     * @return
     */
    @Cachezable(key = "#id", prefix = KEY, expire = 36000)
    public Map<String,Object> getUser(Integer id) {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("id",id);
        result.put("index",index.getAndIncrement());
        return result;
    }

    @Cachezable(key = "ALL", prefix = KEY, expire = 36000)
    public Map<String,Object> getUserAll() {
        Map<String,Object> result = new HashMap<String,Object>();
        for (int i = 0; i < 10; i++){
            result.put("id",   100000 + i);
            result.put("index",index.getAndIncrement());
        }
        return result;
    }

    @Cachezable(key = "#id,ALL", prefix = KEY, type = Cachezable.CachezType.REMOVE)
    public int removeUser(Integer id){
        return id;
    }

    @Ignore
    @Cachezable(key = "#id", prefix = KEY, type = Cachezable.CachezType.UPDATE, updateExc = "userService#getUser", updateExcArgs = "#id")
    public int updateUser(Integer id){
        return id;
    }
}
