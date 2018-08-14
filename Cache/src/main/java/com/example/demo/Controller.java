package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserServiceImpl userService;

    Logger logger= LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/add")
    public void add(){
        User u=new User();
        u.setId(1);
        u.setName("DaVi");
        userService.addUser(u);
    }

    @RequestMapping("/delete")
    public void delete(){
        userService.deleteUserById();
    }
    @RequestMapping("/findall")
    public List<User> findall(){
        List<User> list= userService.getUserList();
        logger.info(list.toString());
        return list;
    }



}
