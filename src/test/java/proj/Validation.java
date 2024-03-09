package proj;

public class Validation extends Excel {
	public static int conversion(String price) {
		String rePrice ="";
		for(char c:price.toCharArray()) {
			if(Character.isDigit(c)) {
				rePrice=rePrice+c;
			}
		}
		return Integer.parseInt(rePrice);
	}
	
	public static void validation(String price1,String price2,String price3) {
		int product1,product2,totalPrice;
		
		product1=conversion(price1);
		System.out.println(product1);
		
		product2=conversion(price2);
		System.out.println(product2);
		
		totalPrice=conversion(price3);
		System.out.println(totalPrice);
		
		if(product1+product2==totalPrice) {
			System.out.println("Automation Successful");
		}
		else
		{
			System.out.println("Automation not  Successful");

		}
	}
}
