package com.ssafy.enjoy.config;

import com.ssafy.enjoy.interceptor.ConfirmInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

@Configuration
//@MapperScan(basePackages = {"com.ssafy.enjoy.*.dao"}) // properties에서 설정
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final List<String> patterns = Arrays.asList("/admin");

    private ConfirmInterceptor confirmInterceptor;

    public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
        this.confirmInterceptor = confirmInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "UPDATE", "DELETE", "PATCH")
                .maxAge(1000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
}
