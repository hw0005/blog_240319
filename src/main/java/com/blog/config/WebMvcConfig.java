package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.blog.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(
			ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**") // web path http://localhost/images/aaaa_1723533796962/pngtree-vector-chat-icon-png-image_322158.jpg
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 위치
		
	}
}
