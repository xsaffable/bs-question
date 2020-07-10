package com.demo;

import com.demo.interceptor.UserLoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 启动类
 * @author affable
 */
@SpringBootApplication
@MapperScan(basePackages = "com.demo.dao")
public class WebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		System.out.println("run,success");
	}

	/**
	 * @description WebMvc 配置
	 * @author affable
	 * @date 2020-05-15 16:42
	 *
	 */
	@Configuration
	static class WebMvcConfiguration extends WebMvcConfigurationSupport {

		private final UserLoginInterceptor userLoginInterceptor;

		@Autowired
		public WebMvcConfiguration(UserLoginInterceptor userLoginInterceptor) {
			this.userLoginInterceptor = userLoginInterceptor;
		}

		@Override
		protected void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(userLoginInterceptor)
					.addPathPatterns("/**")
					// 去除登陆页面跳转和登录接口
					.excludePathPatterns("/login")
					.excludePathPatterns("/login/login")
					.excludePathPatterns("/login/logout")
					.excludePathPatterns("/reg")
					.excludePathPatterns("/view/index")
					.excludePathPatterns("/static/**");
		}

		@Override
		protected void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/static/**")
					.addResourceLocations("classpath:/static/");
		}
	}

}
