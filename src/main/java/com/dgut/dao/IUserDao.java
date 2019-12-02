package com.dgut.dao;

import com.dgut.entity.*;
import com.dgut.queryVo.queryVo;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    /**
     * 查找所有用户
     * @return
     */
    @Select("select * from user")
    public List<User> findAllUser();

    /**
     * 查找一个用户
     * @param id
     * @return
     */
    @Select("select * from user where user_id = #{user_id}")
    @Results(id = "userMap",
            value = {
            @Result(id = true,column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "user_password",property = "user_password"),
            }
    )
    public User findOneUser(Integer id);

    /**
     * 添加一个用户
     * @param user
     */
    public void addOneUser(User user);

    /**
     * 删除一个用户
     * @param id
     */
    public void deleteOneUser(Integer id);

    /**
     * 更新一个用户
     * @param user
     */
    public void updateOneUser(User user);

    /**
     * 根据查询条件查询用户
     * @param user_name
     */
    public User findByCondition(String user_name);

    /**
     * 通过关键字查询用户
     * @param keyWord
     */
    public List<User> findByOneFuzzy(String keyWord);

    /**
     * 根据两种条件查询用户
     * @param keyWord
     * @param id
     * @return
     */
    public List<User> findByTwoFuzzy(String keyWord,Integer id);

    /**
     * 通过多种id查询用户
     * @param
     * @return
     */
    public List<User> findByIds(queryVo vo);

    /**
     * 一对一查询账户和用户
     * @return
     */
    @Select("select * from account , user where user_id = uid")
    @Results(id = "accountToUser",
    value = {@Result(id = true,column = "account_id",property = "account_id"),
             @Result(column = "money",property = "money"),
             @Result(column = "uid",property = "uid"),
             @Result(column = "uid",property = "user",one = @One(select = "com.dgut.dao.IUserDao.findById",fetchType = FetchType.LAZY ))
    }
    )
    public List<Account> findAccountUser();

    /**
     * 一对多查询
     * @return
     */
    public List<User> findUserAccounts();

    /**
     * 查询所有的用户以及他们关联的角色
     * @return
     */
    public  List<User> findUsersWithRoles();

    /**
     * 一对一延迟加载账户
     * @return
     */
    public List<Account> findLazyUser();

    /**
     * 一对一延迟加载用户，用在配置文件查询
     * @return
     */
    public User findById(Integer uid);

    /**
     * 一对多查询所有用户
     * @return
     */
    public List<User> findLazyAccount();

    /**
     * 一对多延迟加载账户，用在配置文件查询
     * @param user_id
     * @return
     */
    public List<Account> findByUserId(Integer user_id);
}
