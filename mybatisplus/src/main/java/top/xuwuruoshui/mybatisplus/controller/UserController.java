package top.xuwuruoshui.mybatisplus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.xuwuruoshui.mybatisplus.mapper.UserMapper;
import top.xuwuruoshui.mybatisplus.pojo.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User selectById(@PathVariable("id")int id){
        return userMapper.selectById(id);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public List<User> selectList(){
        return userMapper.selectList(null);
    }

    /**
     * 插入
     */
    @PostMapping("/insert")
    public void insertUser(){
        User user = new User();
        user.setAge(4);
        user.setEmail("102222123@qq.com");
        user.setName("qqwed");
        int result = userMapper.insert(user);
        log.info(result+"");
    }

    /**
     * 修改
     */
    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable("id")int id){
        User user = userMapper.selectById(id);
        user.setId(id);
        user.setName("ssss");
        user.setEmail("ssss@qq.com");
        int result = userMapper.updateById(user);
        log.info(result+"");
    }

    /**
     * Map查询
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public List<User> selectUserByName(@PathVariable("name")String name){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        List<User> users = userMapper.selectByMap(map);
        return users;
    }

    /**
     * 根据id批量查询
     * @param id1
     * @param id2
     * @param id3
     * @return
     */
    @GetMapping("/{id1}/{id2}/{id3}")
    public List<User> selectUserByIdArray(@PathVariable("id1")int id1,@PathVariable("id2")int id2,@PathVariable("id3")int id3){
        return userMapper.selectBatchIds(Arrays.asList(id1, id2, id3));
    }

    /**
     * 分页查询
     */
    @GetMapping("/{page}/{size}")
    public List<User> selectUserLimt(@PathVariable("page")long page,@PathVariable("size")long size){
        Page<User> pages = new Page<>(page,size);
        userMapper.selectPage(pages, null);
        return pages.getRecords();
    }

    /**
     * 批量删除
     * 删除是不需要更改version的，因为这是幂等，无论删多少次最终结果都是被删掉
     * @param arr
     */
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("id")List arr){
        int result = userMapper.deleteBatchIds(arr);
        log.info(result+"");
    }

}
