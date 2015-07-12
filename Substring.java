//solving the palindromic problem using DP
public class Substring {
    public String longestPalindrome(String s) {
		if (s == null){
			return null;
		}
		int n = s.length();
		if(n == 1){
			return s;
		}
		char [] sArr = s.toCharArray();
		//some edge cases
		boolean[][] arr = new boolean[n][n];
		for(int i  = 0; i < n; i ++){
			arr[i][i] = true;
		}
		int max = 1;
		int startInd = 0;
		for(int length = 2; length <= n; length++){
			for (int i = 0; i < n - length + 1; i++){
				// System.out.println(i + ": " + sArr[i] + "," + (i + length - 1) + sArr[i+ length - 1]);
				if (sArr[i] == sArr[i + length - 1]){
					if ((length <= 2) || (length > 2 && arr[i + 1][i + length - 2])){
						arr[i][i + length - 1] = true;
						if (length > max){
							max = length;
							startInd = i;
						}
						continue;
					}
				}
				arr[i][i + length - 1] = false;
			}
		}
		// for (int i = 0; i < n; i++){
		// 	for (int j = 0; j < n; j++){
		// 		System.out.print(arr[i][j] + "\t");
		// 	}
		// 	System.out.print("\n");
		// }
		// System.out.println("max" + max);
		return s.substring(startInd, startInd + max);
    }

    public static void main(String argv[]){
    	String s = "bb";
    	Substring b = new Substring();
    	System.out.println(b.longestPalindrome(s));
    }
}