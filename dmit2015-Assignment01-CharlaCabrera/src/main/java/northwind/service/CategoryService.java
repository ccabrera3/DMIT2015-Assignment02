package northwind.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import java.util.List;
import java.util.logging.Logger;

import northwind.data.CategoryRepository;
import northwind.model.Category;
@Stateless
public class CategoryService {

@Inject
private CategoryRepository categoryRepository;
@Inject
private Logger log;

public List<Category> findAll(){
	return categoryRepository.findAll();
}

public Category findOne(int categoryId) {
	Category currentCategory = null;
	try {
		currentCategory = categoryRepository.find(categoryId);
	} catch(NoResultException e) {
		log.fine(e.getMessage());
		
	}
	return currentCategory;
}



public void createCategory(String categoryName, String description, byte[] picture) {
	Category currentCategory = new Category();
	currentCategory.setCategoryName(categoryName);
	currentCategory.setDescription(description);
	currentCategory.setPicture(picture);
	createCategory(currentCategory);
}

public void createCategory(Category currentCategory) {
	categoryRepository.persist(currentCategory);
}

public Category updateCategory(Category currentCategory) {
	return categoryRepository.edit(currentCategory);
}

public void deleteCategory(int categoryId) {
	Category currentCategory = findOne(categoryId);
	if(currentCategory != null) {
		categoryRepository.remove(currentCategory);
	}
}




}
