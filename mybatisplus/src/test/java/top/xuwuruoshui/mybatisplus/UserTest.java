package top.xuwuruoshui.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.xuwuruoshui.mybatisplus.mapper.UserMapper;
import top.xuwuruoshui.mybatisplus.pojo.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;


   @Test
   public  void conditionSelect(){
       QueryWrapper<User> wrapper = new QueryWrapper<>();
       //等于 大于 小于
       wrapper.eq("name","qqwed")
               .eq("email","222222@qq.com")
               .le("age",99)
               .ge("id",20);
       User user = userMapper.selectOne(wrapper);
       log.info(user+"");
   }

    @Test
    public  void conditionSelect1(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //between
        wrapper.between("age",20,30);
        List<User> user = userMapper.selectList(wrapper);
        user.forEach(System.out::println);
    }


    @Test
    public  void conditionSelect3(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //in
        wrapper.in("age", 12,13,14);
        List<User> user = userMapper.selectList(wrapper);
        user.forEach(System.err::println);
    }

    @Test
    public  void conditionSelect4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //in
        wrapper.like("name","qq%");
        List<User> user = userMapper.selectList(wrapper);
        user.forEach(System.err::println);
    }

    
}
