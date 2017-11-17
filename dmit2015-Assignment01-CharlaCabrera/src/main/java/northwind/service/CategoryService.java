package northwind.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import northwind.data.CategoryRepository;
import northwind.model.Category;
@Stateless
public class CategoryService {

@Inject
private CategoryRepository categoryRepository;
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


}
