# Usage
> add to your config package
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.xuwuruoshui.swaggerdemo.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo((apiInfo()));
    }


    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("来自M78星云的MADAO")
                .description("interesting project")
                .version("0.1")
                .contact(new Contact("xuwuruoshui","top.xuwuruoshui","1085252985@qq.com"))
                .license("2333")
                .licenseUrl("http://top.xuwuruoshui")
                .build();
    }


}
```
- add `@Api(tags="xxx")` to your controller's class in order to introduct some stuffs
- add `@ApiOperation("xxx")` to indicate what the function is useful
- add `@ApiImplicitParam(name = "id",value = "user'id",defaultValue = "2",required = true)` to method, to complete some informations 
