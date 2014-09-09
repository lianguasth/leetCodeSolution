package palindrome;

public class Solution {
    public boolean isPalindrome(String s) {
    	if(s.length() == 0){
    		return true;
    	}
    	int n = s.length();
    	int k = n-1;
    	boolean flag = true;
    	for(int i = 0; i < n/2; i++,k--){
    		char front = s.charAt(i);
    		char end = s.charAt(k);
    		boolean ff = (front >= 'a' && front <= 'z') || (front>='A' && front <='Z') || (front >= '0' && front <='9');
    		boolean fe = (end >= 'a' && end <= 'z') || (end>='A' && end <='Z') || (end >= '0' && end <='9');
    		if(!ff && !fe){
    			continue;
    		}else if(!ff){
    			k++;
    			continue;
    		}else if(!fe){
    			i--;
    			continue;
    		}else{
    			if(!(front - 'a' == end - 'a' || front - 'a' == end - 'A' || front - 'A' == end - 'a')){
    				flag = false;
    				break;
    			}
    		}
    	}
    	return flag;
//    	String filteredS = new String();
//        for(int i = 0; i < s.length(); i++){
//        	char a = s.charAt(i);
//        	if((a >= 'a' && a <= 'z') || (a>='A' && a <='Z')){
//        		filteredS += a;
//        	}
//        }
//        filteredS = filteredS.toLowerCase();
//        //System.out.println(filteredS);
//        boolean flag = true;
//        for(int i = 0; i < filteredS.length()/2; i++){
//        	if(filteredS.charAt(i) != filteredS.charAt(filteredS.length() - i -1)){
//        		flag = false;
//        	}
//        }
//        return flag;
    }
    
    public static void main(String argv[]){
    	Solution s= new Solution();
    	System.out.println(s.isPalindrome("1a2"));
    	System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
