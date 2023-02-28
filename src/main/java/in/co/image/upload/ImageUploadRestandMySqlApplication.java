package in.co.image.upload;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.image.upload.entity.Category;
import in.co.image.upload.service.CateService;
import in.co.image.upload.service.ImageService;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class ImageUploadRestandMySqlApplication {
	
	
	@Autowired
	private ImageService imageservice;
	
	@Autowired
	private CateService cateservice;
	

    @PostMapping	
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException
	{
		String uploadimage = imageservice.uploadimage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadimage);
	}
    
    @PostMapping("/category")
    public Category Add(@RequestBody Category cate)
    {
    	return this.cateservice.Addcategory(cate);
    }
    
    @GetMapping("/allCategory")
    public List<Category> List(){
    	return this.cateservice.list();
    }
    
    @PutMapping("/category/{categoryid}")
    public ResponseEntity<HttpStatus> Update(@PathVariable(value = "categoryid") long categoryid)
    {
    	try {
    		System.out.println("categoryUpdate");
			this.cateservice.update(categoryid);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @DeleteMapping("/category/{catid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "catid") long catid)
    {
    	try {
    		System.out.println("Delete");
			this.cateservice.delete(catid);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
    }
    
    @GetMapping("/{filename}")
    public ResponseEntity<?> downloadimage(@PathVariable String filename)
    {
    	byte[] imagedata = imageservice.downloadImage(filename);
    	return ResponseEntity.status(HttpStatus.OK)
    			.contentType(MediaType.valueOf("image/png"))
    			.body(imagedata);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(ImageUploadRestandMySqlApplication.class, args);
	}

}
