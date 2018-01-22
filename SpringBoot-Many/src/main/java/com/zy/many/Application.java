package com.zy.many;


import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;



//@ServletComponentScan
@ComponentScan
@SpringBootConfiguration
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类（项目发布，打包的时候用到）
        return builder.sources(Application.class);
    }
    public static void main(String[] args) {
    	
    	SpringApplication.run(Application.class, args);
    }
    
    @Autowired
    private RestTemplateBuilder builder;
    //自主创建spring bean;如创建serverrunner的bean;让mina的serverrunner 可以随着启动类一起启动
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
   
    /**  
     * 文件上传配置  
     * @return  
     */  
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //文件最大  
        factory.setMaxFileSize("10240KB"); //KB,MB ==10M 
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("102400KB");  
        return factory.createMultipartConfig();  
    }
    /**
     * 错误信息跳转页面
     * */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
               /* ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");*/
                container.addErrorPages(error401Page, error404Page);
            }
            
        };
    }
}
