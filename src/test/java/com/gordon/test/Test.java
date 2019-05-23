package com.gordon.test;

import com.gordon.DAO.IUserDAO;
import com.gordon.domain.User;
import com.gordon.domain.sqlSessionFactoryUtils;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactoryUtils.openSqlSession();
            IUserDAO userDAO = sqlSession.getMapper(IUserDAO.class);
            List<User> users = userDAO.findAll();
            for (User user :
                    users) {
                System.out.println(user);

            }
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }

    }

}
