package top.xuwuruoshui.swaggerdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.xuwuruoshui.swaggerdemo.pojo.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "用户相关操作")
@RequestMapping("/api/user")
public class UserController {
    private static  final List<User> list = new ArrayList();

    static {
        list.add(new User(1, "Bob", 12));
        list.add(new User(2, "Joe", 15));
        list.add(new User(3, "Jenny", 11));
        list.add(new User(4, "Harry", 20));
        list.add(new User(5, "Petter", 17));
    }

    @GetMapping("list")
    @ApiOperation("查询所有用户")
    public ResponseEntity<List<User>> userList(){
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public ResponseEntity addUser(@RequestBody User user){
        if (user==null){
            return new ResponseEntity("add failed", HttpStatus.BAD_REQUEST);
        }
        list.add(user);
        return new ResponseEntity("add successfully", HttpStatus.OK);
    }

    @PutMapping("edit")
    @ApiOperation("修改用户")
    public ResponseEntity editUser(@RequestBody User user){

        if (user==null){
            return new ResponseEntity("edit failed", HttpStatus.BAD_REQUEST);
        }
        for (User user1 : list) {
           if(user1.getId()==user.getId()){
            list.set(list.indexOf(user1),user);
            return new ResponseEntity("add successfully", HttpStatus.OK);
           }
        }
        return new ResponseEntity("none user", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public ResponseEntity deletUser(@RequestParam(value = "id",required = true) int gid){

        for (User user1 : list) {
            if(user1.getId()==gid){
                list.remove(user1);
                return new ResponseEntity("del successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity("del error", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{id}")
    @ApiOperation("查询用户")
    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "2",required = true)
    public ResponseEntity getUser(@RequestParam(required = true) int id){

        for (User user : list) {
           if(user.getId()==id)
           {
               return new ResponseEntity(user, HttpStatus.OK);
           }
        }
        return new ResponseEntity("404 Not Foud", HttpStatus.NOT_FOUND);
    }



}
