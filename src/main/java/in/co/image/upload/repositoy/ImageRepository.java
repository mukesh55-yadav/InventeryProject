package in.co.image.upload.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.image.upload.entity.ImageData;

public interface ImageRepository extends JpaRepository<ImageData, Long> {
	
	Optional<ImageData> findByName(String imageName);
}
