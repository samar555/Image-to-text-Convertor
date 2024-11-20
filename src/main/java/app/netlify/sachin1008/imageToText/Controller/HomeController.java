package app.netlify.sachin1008.imageToText.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Controller
public class HomeController {
 
	
	@Value("${upload.directory}")
	private String uploadDirectory;
	@Value("${TESSDATA_PREFIX}")
	private String tessData;
	
	@GetMapping("/")
	public String homepage() {
		return "home";
	}
	
	@PostMapping("/upload")
	public String ScannImage(@RequestParam("file") MultipartFile file,Model model) {
		System.out.println("entering in the upload");
		if(file.isEmpty()) {
			model.addAttribute("message","Please Select The File");
			return "home";
		}
		try {
			File directory=new File(uploadDirectory);
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			byte[] bytes=file.getBytes();
			Path path=Paths.get(uploadDirectory +file.getOriginalFilename());
			Files.write(path, bytes);
			model.addAttribute("message","File uploaded successfully fatching data");
			model.addAttribute("fileName",file.getOriginalFilename());
			ITesseract tess=new Tesseract();
			tess.setDatapath(tessData);
			String str=tess.doOCR(new File(uploadDirectory+file.getOriginalFilename()));
			model.addAttribute("result",str);
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	
}
