package in.co.image.upload.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import in.co.image.upload.entity.Category;
import in.co.image.upload.repositoy.CategoryRepo;

@Service
public class CateService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	public Category Addcategory(Category cate)
	{
		return categoryRepo.save(cate);
	}
	
	public List<Category> list()
	{
		return (List<Category>) categoryRepo.findAll();
	}
	
	public void update(long categoryid)
	{
		System.out.println("Update");
		Category cid = categoryRepo.getById(categoryid);
		categoryRepo.saveAndFlush(cid);
	}
	
	public void delete(long id)
	{
		Category cat = categoryRepo.getById(id);
		categoryRepo.delete(cat);
	}

}
