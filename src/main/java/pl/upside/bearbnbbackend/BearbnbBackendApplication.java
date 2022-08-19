package pl.upside.bearbnbbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.upside.bearbnbbackend.services.FileStorageService;

import javax.annotation.Resource;

@SpringBootApplication
public class BearbnbBackendApplication {
	@Resource
	FileStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(BearbnbBackendApplication.class, args);
	}

}
