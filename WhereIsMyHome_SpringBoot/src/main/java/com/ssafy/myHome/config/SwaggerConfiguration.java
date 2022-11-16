package com.ssafy.myHome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 스프링 실행시 설정파일
@SuppressWarnings("unchecked") // warning 제거
public class SwaggerConfiguration {
//	http://localhost:9999/swagger-ui/index.html
	
//	private String version = "V1";
//	private String title = "SSAFY Board-Vuejs API " + version;
//
//	private ApiInfo apiInfo() {
//		String descript = "SSAFY Vuejs API Reference for Developers<br>";
//		descript += "<img src=\"http://localhost:9999/vue/static/assets/img/ssafy_logo.png\">";
//		return new ApiInfoBuilder().title(title).description(descript)
////				.termsOfServiceUrl("https://edu.ssafy.com")
//				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("SSAFY License")
//				.licenseUrl("ssafy@ssafy.com").version("1.0").build();
//	}
//	
	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}

}
