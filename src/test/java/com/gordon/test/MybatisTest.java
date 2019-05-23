package com.gordon.test;

import com.gordon.DAO.IUserDAO;
import com.gordon.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDAO userDAO;


    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口代理对象
        userDAO = sqlSession.getMapper(IUserDAO.class);
    }

    @After  //用于在测试方法之后执行
    public void destroy() throws Exception {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<User> users = userDAO.findAll();
        for (User user :
                users) {
            System.out.println(user);
        }
    }


    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("gordon");
        user.setAddress("重庆市南岸区");
        user.setSex("男");
        user.setBirthday(new Date());
        userDAO.saveUser(user);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(49);
        user.setUsername("gordon");
        user.setAddress("重庆市南岸区");
        user.setSex("女");
        user.setBirthday(new Date());
        userDAO.updateUser(user);

    }
    @Test
    public  void testDelete(){
        userDAO.deleteUser(51);
    }


}
