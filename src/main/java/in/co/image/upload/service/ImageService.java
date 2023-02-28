package in.co.image.upload.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.co.image.upload.entity.ImageData;
import in.co.image.upload.repositoy.ImageRepository;
import in.co.image.upload.util.ImageUtil;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imagerepo;

	public String uploadimage(MultipartFile file) throws IOException {
		
		ImageData imageData = imagerepo.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtil.compressImage(file.getBytes())).build());
		
		if (imageData != null) {
			return "Image Upload successfully"+file.getOriginalFilename();
		}
		return null;
	}
	
	public byte[] downloadImage(String filename)
	{
		Optional<ImageData> dbImage = imagerepo.findByName(filename);
		byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
		return image;
	}
	
	
	
}
