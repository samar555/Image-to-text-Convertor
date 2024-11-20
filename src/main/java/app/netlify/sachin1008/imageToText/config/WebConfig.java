package app.netlify.sachin1008.imageToText.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${upload.directory}")
	private String userDirectory;
	@Value("${TESSDATA_PREFIX}")
	private String tessData;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("file:"+userDirectory);
//		registry.addResourceHandler("/**")
//        .addResourceLocations("file:" + tessData);
	}
	
	
}
