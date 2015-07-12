import java.util.*;
public class WordBreak{
	public boolean wordBreak(String s, Set<String> dict) {
    	if (s == null){
    		return false;
    	}
    	int n = s.length();
    	int maxLen = 0;
    	for (String ts : dict){
    		maxLen = Math.max(maxLen, ts.length());
    	}
    	boolean arr[] = new boolean[n + 1];
    	arr[0] = true;
    	for (int i = 0; i < n; i++){
    		if (arr[i]){
    			for (int j = 1; j <= maxLen; j++){
	    			if (i + j <= n && dict.contains(s.substring(i, i + j))){
	    				arr[i + j] = true;
	    			}
	    		}
    		}
    	}
    	return arr[n];
    }
}