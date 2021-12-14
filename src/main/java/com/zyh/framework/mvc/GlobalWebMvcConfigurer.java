package com.zyh.framework.mvc;


import com.zyh.utils.UploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author
 * @date 2021/3/25 18:04
 */
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private UploadConfig config;

    @Bean
    public TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/f/**",config.getAccessPath());
    }

    //使用CORSFilter解决跨域问题
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration= new CorsConfiguration();
        //允许跨域请求的域名
        corsConfiguration.addAllowedOrigin("*");
        //允许跨域请求的方法
        corsConfiguration.addAllowedMethod("*");
        //允许任何头部
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        return corsFilter;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler对外暴露的访问位置
        //addResourceLocations内部文件放置的目录
        registry.addResourceHandler(config.getAccessPath())
                .addResourceLocations("file:"+config.getUploadFolder());
    }
}
