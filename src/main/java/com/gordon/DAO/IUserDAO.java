package com.gordon.DAO;

import com.gordon.domain.User;

import java.util.List;

public interface IUserDAO {
    /**
     * 查询所有操作
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);

}
