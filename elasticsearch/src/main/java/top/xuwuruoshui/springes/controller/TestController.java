package top.xuwuruoshui.springes.controller;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.web.bind.annotation.*;
import top.xuwuruoshui.springes.pojo.Person;


@RestController
@RequestMapping("/")
public class TestController {

  private final ElasticsearchRestTemplate elasticsearchRestTemplate;

  public TestController(ElasticsearchRestTemplate elasticsearchRestTemplate) {
    this.elasticsearchRestTemplate = elasticsearchRestTemplate;
  }

  @PostMapping("/person")
  public String save(@RequestBody Person person) {

    IndexQuery indexQuery = new IndexQueryBuilder()
      .withId(person.getId()+"")
      .withObject(person)
      .build();

    String documentId = elasticsearchRestTemplate.index(indexQuery,IndexCoordinates.of("person"));
    return documentId;
  }

  /**
   * 根据Id删除
   */
  @DeleteMapping("/person/del/{id}")
  public void delPerson(@PathVariable("id") String id){

    String delete = elasticsearchRestTemplate.delete(id,IndexCoordinates.of("person"));
    System.out.println(delete);
  }

  /**
   * 批量删除
   */
  @DeleteMapping("/person/bulkdel/{name}")
  public void bulkDelPerson(@PathVariable("name") String name){
    TermQueryBuilder builder = QueryBuilders.termQuery("name", name);
    Query query = new NativeSearchQuery(builder);
    elasticsearchRestTemplate.delete(query,Person.class,IndexCoordinates.of("person"));
  }

  /**
   * 删除doc
   */
  @DeleteMapping("/person/del/doc")
  public void delPersionDoc(){
    Person person = new Person();
    String result = elasticsearchRestTemplate.delete(person,IndexCoordinates.of("person"));
    System.err.println(result);
  }


  /**
   * 根据Id查询
   * @param id
   * @return
   */
  @GetMapping("/person/search/{id}")
  public Person findById(@PathVariable("id")  Long id) {
    /*Person person = elasticsearchOperations
      .queryForObject(GetQuery.getById(id.toString()), Person.class);*/
    Person person = elasticsearchRestTemplate.get(id + "", Person.class,IndexCoordinates.of("person"));

    return person;
  }

  @GetMapping("/person/search1")
  public SearchHits<Person> search1(){

    Query query = new CriteriaQuery(new Criteria("name").is("das"));


    SearchHits<Person> search = elasticsearchRestTemplate.search(query, Person.class);
    System.out.println(search);
    return search;
  }

  @GetMapping("/person/search2")
  public SearchHits<Person> search2(){

    TermQueryBuilder builder = QueryBuilders.termQuery("name", "xxxx");
    Query query = new NativeSearchQuery(builder);
    SearchHits<Person> search = elasticsearchRestTemplate.search(query, Person.class);
    System.out.println(search);
    return search;
  }


}