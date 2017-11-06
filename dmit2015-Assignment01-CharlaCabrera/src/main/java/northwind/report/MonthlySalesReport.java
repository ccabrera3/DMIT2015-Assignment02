package northwind.report;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class MonthlySalesReport {


		private int month;

		private BigDecimal yearsales; 
		
		
		public MonthlySalesReport(int month, BigDecimal yearsales) {
			super();
			this.month = month;
			this.yearsales = yearsales;
		}
		
		public MonthlySalesReport(int month, double yearsales) {
			super();
			this.month = month;
			this.yearsales = BigDecimal.valueOf(yearsales).setScale(2, RoundingMode.HALF_UP);
		}
		
	
		//GETTERS AND SETTERS
		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public BigDecimal getYearsales() {
			return yearsales;
		}

		public void setYearsales(BigDecimal yearsales) {
			this.yearsales = yearsales;
		}
		



	

}
