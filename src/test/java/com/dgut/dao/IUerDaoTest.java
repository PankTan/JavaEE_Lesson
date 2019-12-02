package com.dgut.dao;


import com.dgut.entity.Account;
import com.dgut.entity.User;
import com.dgut.queryVo.queryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IUerDaoTest  {

    private InputStream inputStream;
    private SqlSession sqlSession;
    private SqlSessionFactory factory;
    private IUserDao userDao;

    @Before
    public void before() throws Exception{
        String resource = "SqlMapConfig.xml";
        inputStream= Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void findAllUser(){

        List<User> allUser = userDao.findAllUser();
        for (User user:allUser){
            System.out.println(user);
        }
    }

    @Test
    public void findOneUser(){
        User oneUser = userDao.findOneUser(1);
        System.out.println(oneUser);
    }

    @Test
    public void addOneUser(){
        User user = new User();
        user.setUser_id(5);
        user.setUser_name("admin5");
        user.setUser_password("admin5");
        userDao.addOneUser(user);
    }

    @Test
    public void deleteOneUser(){
        userDao.deleteOneUser(5);
    }

    @Test
    public void updateOneUser(){
        User user = new User();
        user.setUser_id(5);
        user.setUser_name("admin0");
        user.setUser_password("admin0");
        userDao.updateOneUser(user);
    }

    @Test
    public void findByCondition(){
        User user = userDao.findByCondition("admin1");
        System.out.println(user);
    }

    @Test
    public void findByOneFuzzy(){
        List<User> users = userDao.findByOneFuzzy("%mi%");
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void findByTwoFuzzy(){
        List<User> users = userDao.findByTwoFuzzy("%mi%", 2);
        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void findByIds(){
        List ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        queryVo queryVo = new queryVo();
        queryVo.setIds(ids);

        List<User> users = userDao.findByIds(queryVo);

        System.out.println(users);
    }

    @Test
    public void findAccounttoUser(){
        List<Account> accountUser = userDao.findAccountUser();
        System.out.println(accountUser);
    }

    @Test
    public void findUserAccounts(){
        List<User> userAccounts = userDao.findUserAccounts();
        System.out.println(userAccounts);
    }

    @Test
    public void findUsersWithRoles(){
        List<User> usersWithRoles = userDao.findUsersWithRoles();
        System.out.println(usersWithRoles);
    }

    @Test
    public void findLazyUser(){
        List<Account> lazyUser = userDao.findLazyUser();
        System.out.println(lazyUser.get(0).getMoney());
    }

    @Test
    public void findLazyAccount(){
        List<User> lazyAccount = userDao.findLazyAccount();
        System.out.println(lazyAccount.get(0).getUser_name());
    }

    @Test
    public void findOneCache(){
        User user1 = userDao.findById(1);
        System.out.println(user1);
        User user2 = userDao.findById(1);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void findTwoCache(){
        User user1 = userDao.findById(1);
        System.out.println(user1);
        sqlSession.close();

        SqlSession sqlSession1 = factory.openSession(true);
        IUserDao userDao1 = sqlSession1.getMapper(IUserDao.class);
        User user2 = userDao1.findById(1);
        System.out.println(user2);

        System.out.println("二级缓存" + (user1 == user2));
    }
}
