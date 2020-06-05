package top.xuwuruoshui.springes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xuwuruoshui.springes.dao.PersonRepository;
import top.xuwuruoshui.springes.pojo.Person;

import java.util.List;

@RestController
@RequestMapping("/")
public class Test1Controller {
    private final PersonRepository personRepository;

    public Test1Controller(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/test")
    public void findByNameAndPrice(@RequestParam("name") String name,@RequestParam("age") int age){
        List<Person> list = personRepository.findByNameAndAge(name, age);
        System.out.println(list);
    }
}
