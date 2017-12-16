package schumi;

import java.math.BigDecimal;

public class Insurance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		isIns();
		//System.out.println("**********");
		//isBank();
		System.out.println("**********");
		//monthlyDeposit();
	}
	
	private static void isIns(){
		int year=18;
		BigDecimal yPay=new BigDecimal(150000);
		BigDecimal rate = new BigDecimal(1.03);
		BigDecimal sum = new BigDecimal(0);
		for (int i=36; i<80;i++){
			if (i < 56){
				sum = (sum.add(yPay)).multiply(rate);
			} else {
				sum = sum.multiply(rate);
			}
			System.out.println("Year " + i + " : " + sum.setScale(0, BigDecimal.ROUND_HALF_UP));
		}
	}
	
	private static void isBank(){
		int year=18;
		BigDecimal rate = new BigDecimal(1.03);
		BigDecimal sum = new BigDecimal(62000);
		for (int i=1; i<year;i++){
			sum = sum.multiply(rate);
			System.out.println("Year " + i + " : " + sum.setScale(0, BigDecimal.ROUND_HALF_UP));
		}
	}
	
	private static void monthlyDeposit(){
		int year=2;
		BigDecimal rate = new BigDecimal(1.036);
		BigDecimal rateBase = new BigDecimal(1);
		int months=year * 12;
		BigDecimal rateM = (rate.subtract(rateBase)).divide(new BigDecimal(12)).add(rateBase);
		BigDecimal monthlyDeposite = new BigDecimal(300);
		
		BigDecimal sum = new BigDecimal(18561.95);
		for (int i=0; i<months;i++){
			sum = (sum.add(monthlyDeposite)).multiply(rateM);
			System.out.println("Month " + i + " : " + sum.setScale(0, BigDecimal.ROUND_HALF_UP));
		}
	}

}
