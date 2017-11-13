package com.tincher.appcraft.db;


import com.tincher.appcraft.db.entity.UserDao;

/**
 * Created by dks on 2017/11/7.
 */

public class EntityManager {
    private static EntityManager entityManager;
    private UserDao userDao;

    /**
     * 创建User表实例
     *
     * @return
     */
    public UserDao getUserDao() {
        userDao = DaoManager.getInstance().getSession().getUserDao();
        return userDao;
    }

    /**
     * 创建单例
     *
     * @return
     */
    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }
}
