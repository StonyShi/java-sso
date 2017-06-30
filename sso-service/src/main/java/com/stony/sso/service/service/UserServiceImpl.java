package com.stony.sso.service.service;

import com.stony.sso.service.helper.PasswordHelper;
import com.google.common.collect.Sets;
import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.commons.CollectionUtil;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.enums.UserStatus;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 15:53 </p>
 * <p>Version: 1.0 </p>
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final String REMOVE_WATCH_KEYS = "UserNames,UserIds";
    @javax.annotation.Resource
    private UserMapper userMapper;
    @javax.annotation.Resource
    private PasswordHelper passwordHelper;


    /**
     * 创建用户
     *
     * @param user
     */
    @Cachezable(key = "ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userMapper.insert(user);
        return user;
    }

    @Override
    @Cachezable(key = "#user.id,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public User updateUser(User user) {
        if (StringUtils.isNotEmpty(user.getSea())) {
            passwordHelper.encryptPassword(user);
        }
        return userMapper.updateByPrimaryKeySelective(user) > 0 ? user : null;
    }

    @Override
    @Cachezable(key = "#userId,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteUser(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setLocked(UserStatus.LOCKED.STATUS);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Cachezable(key = "#userIds,ALL,MAPALL",
            prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int disableUsers(String userIds) {
        Iterable<String> ids = CollectionUtil.split(userIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String userId : _ids) {
            if (null != updateUser(new User(Long.valueOf(userId), UserStatus.LOCKED.STATUS, DateUtils.now()))) {
                i++;
            }
        }
        return i;
    }

    @Override
    @Cachezable(key = "#userId,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int cleanUser(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    @Cachezable(key = "#userId,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public void changePassword(Long userId, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        //不更新字段
        User _user = new User();
        _user.setPassword(user.getPassword());
        _user.setSea(user.getSea());
        _user.setId(user.getId());
        _user.setUpdateDate(DateUtils.now());
        userMapper.updateByPrimaryKeySelective(_user);
    }

    @Override
    @Cachezable(key = "#userId", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.SET, watch = "UserIds")
    public User findOne(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.SET)
    public List<User> findAll() {
        return userMapper.findAll();
    }


    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Cachezable(key = "#username", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.SET, watch = "UserNames")
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        logger.info("findByUsername = {}", user);
        return user;
    }

    @Override
    @Cachezable(key = "MAPALL", prefix = SecurityKeys.KEY_FIND_USER, type = Cachezable.CachezType.SET)
    public Map<Long, User> findUsers() {
        List<User> users = findAll();
        Map<Long, User> map = new HashMap<>();
        for (User user : users) {
            map.put(user.getId(), user);
        }
        return map;
    }

    @Override
    public List<User> findUserByRole(String roleIds) {
        return userMapper.findUserByRole(roleIds);
    }
}
