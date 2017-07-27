package com.stony.sso.facade.service;

import com.stony.sso.facade.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 * <p>Version: 1.0</p>
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user);

    public User updateUser(User user);

    /**
     * 锁定账户，删除
     * @param userId
     */
    int deleteUser(Long userId);

    /**
     * 批量禁用用户
     * @param userIds
     */
    int disableUsers(String userIds);
    /**
     * 完全清除用户
     * @param userId
     */
    int cleanUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    /**
     * 查询一个用户，包含锁定
     * @param userId
     * @return
     */
    User findOne(Long userId);

    /**
     * 查询全部用户，包含锁定
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 返回用户ID，User 键值对
     * @return
     */
    Map<Long, User> findUsers();

    /**
     * 拥有角色的用户列表
     * @param roleIds 角色ID，多个逗号隔开
     * @return
     */
    List<User> findUserByRole(String roleIds);

    /**
     * 获取用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);
}
