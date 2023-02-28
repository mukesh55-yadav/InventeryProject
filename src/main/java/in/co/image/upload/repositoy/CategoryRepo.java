package in.co.image.upload.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.image.upload.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{
	
	

}
