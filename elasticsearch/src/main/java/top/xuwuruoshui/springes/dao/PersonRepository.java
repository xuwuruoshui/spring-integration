package top.xuwuruoshui.springes.dao;

import org.springframework.data.repository.Repository;
import top.xuwuruoshui.springes.pojo.Person;
import java.util.List;

@org.springframework.stereotype.Repository
public interface PersonRepository extends Repository<Person, String> {
  List<Person> findByNameAndAge(String id,int age);
}