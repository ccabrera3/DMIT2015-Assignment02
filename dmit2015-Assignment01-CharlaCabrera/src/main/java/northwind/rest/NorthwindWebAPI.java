package northwind.rest;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import northwind.model.Category;
import northwind.service.CategoryService;

@Path("webapi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NorthwindWebAPI {

	@Inject
	private CategoryService categoryService;
	
	@Path("categories")
	@GET
	public List<Category> findAllCategory() {
		return categoryService.findAll();
	}
	
	@Path("categories/{id}")
	@GET
	public Category findOneCategory(@PathParam("id") int categoryId) {
		return categoryService.findOne(categoryId);
	}
	
	@Path("categories")
	@POST
	public void createCategory(Category newCategory) {
		categoryService.createCategory(newCategory);
	}
	
	@Path("categories")
	@PUT
	public void updateCategory(Category currentCategory) {
		categoryService.updateCategory(currentCategory);
	}
	
	@Path("categories/{id}")
	@DELETE
	public void deleteGenre(@PathParam("id") int categoryId) {
		categoryService.deleteCategory(categoryId);
	}
	

}
