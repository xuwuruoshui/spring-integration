package top.xuwuruoshui.swaggerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
                .title("来自M78星云的装逼怪")
                .description("interesting project")
                .version("0.1")
                .contact(new Contact("xuwuruoshui","top.xuwuruoshui","1085252985@qq.com"))
                .license("The Apache License")
                .licenseUrl("http://top.xuwuruoshui")
                .build();
    }


}
