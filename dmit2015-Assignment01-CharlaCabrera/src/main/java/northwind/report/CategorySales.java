package northwind.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CategorySales {

		private String categoryName;
		private BigDecimal totalCategorySales; 
		
		
		public CategorySales(String categoryName, BigDecimal totalCategorySales) {
			super();
			this.categoryName = categoryName;
			this.totalCategorySales = totalCategorySales;
		}
		
		public CategorySales(String categoryName, double totalCategorySales) {
			super();
			this.categoryName = categoryName;
			this.totalCategorySales = BigDecimal.valueOf(totalCategorySales).setScale(2, RoundingMode.HALF_UP);
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public BigDecimal gettotalCategorySales() {
			return totalCategorySales;
		}

		public void settotalCategorySales(BigDecimal totalCategorySales) {
			this.totalCategorySales = totalCategorySales;
		}
	

}
