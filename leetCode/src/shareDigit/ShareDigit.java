package shareDigit;

public class ShareDigit {
	public static boolean shareDigit(int a, int b){
		int a1 = a % 10;
		int a2 = a/10;
		int b1 = b % 10;
		int b2 = b /10;
		if(a1 == b1 || a1 == b2 || a2 == b1 || a2 == b2){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String argv[]){
//		int a = Math.random()*
		System.out.print(ShareDigit.shareDigit(20, 98));
	}
}
