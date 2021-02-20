package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Interceptor.JwtAuthenticationInterceptor;

@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {
   @Override
   public void addInterceptors(InterceptorRegistry registry) {

       //默认拦截所有路径
       registry.addInterceptor(authenticationInterceptor())
               .addPathPatterns("/**");
   }

   @Bean
   public JwtAuthenticationInterceptor authenticationInterceptor() {
       return new JwtAuthenticationInterceptor();
   }
}
