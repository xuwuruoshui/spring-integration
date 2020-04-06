package top.xuwuruoshui.swaggerdemo.db;

import top.xuwuruoshui.swaggerdemo.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    private static  final List<User> list = new ArrayList();

    static {
        list.add(new User(1, "Bob", 12));
        list.add(new User(2, "Joe", 15));
        list.add(new User(3, "Jenny", 11));
        list.add(new User(4, "Harry", 20));
        list.add(new User(5, "Petter", 17));
    }
}
