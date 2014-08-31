public class Solution {
	static int[] A ={1, 1, 2, 2, 3, 3, 4};
	
    public int singleNumber(int A[]) {
        int length = A.length;
        int result = A[0];
        for(int i = 1; i < length; i++){
        	result = result ^ A[i]; 
        }
        return result;
    }
    
    public static void main(String argv[]){
    	Solution s = new Solution();
    	int res = s.singleNumber(A);
    	System.out.println(res);
    }
}