package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取所有用户列表
     * @return
     */
    @Cacheable(value = "user")
    public List<User> getUserList(){
        System.out.println("getUserList:查找所有用户");
        List<User> userList=new ArrayList<User>();
        userList=userRepository.findAll();
        return  userList;
    }


        /**
     * 新增用户信息
     * @param user 用户信息
     * @return
     */
    @CacheEvict(value = "user", key = "#user.id + 'add'")
    public void addUser(User user) {
        System.out.println("addUser:插入User");
        userRepository.save(user);
    }

    /**
     * 更新用户信息
     * @param User 用户信息
     * @return
     */
    public void updateUserById(User User) {
        userRepository.save(User);
    }

    /**
     * 删除用户信息
     * @param id 主键Id
     */

    @CacheEvict(value = "user")
    public void deleteUserById() {
        User u=new User();
        u.setId(1);
        userRepository.delete(u);
    }

    /**
     * 获取最新的用户
     * @return
     */
    public List<User> getCurrentUserList() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        return userRepository.findAll(sort);

    }

    /**
     * 获取分页的用户
     * @return
     */
    public Page<User> getPageUserList() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable=new PageRequest(0,5,sort);
        return userRepository.findAll(pageable);
    }

}
