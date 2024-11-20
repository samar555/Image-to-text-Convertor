package app.netlify.sachin1008.imageToText;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ImageToTextApplication {
	public static void main(String[] args) {
		System.out.println("server started");
		SpringApplication.run(ImageToTextApplication.class, args);
	}
}
