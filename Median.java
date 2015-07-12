public class Median{
	public double findMedianSortedArrays(int A[], int B[]) {
    	if (A == null || B == null){
    		return 0.0;
    	}
    	int m = A.length;
    	int n = B.length;
    	if (m == 0 && n == 0){
    		return 0.0;
    	}
    	//throw away extreme edge cases
    	int k = (m + n)/2 + 1;
    	if ((m + n)%2 == 0){
    		return (find(A, B, k-1, 0, m-1, 0, n-1) 
    			+ find(A, B, k, 0, m - 1, 0, n - 1))/2.0;
    	}else{
    		return find(A, B, k, 0, m - 1, 0, n - 1);
    	}
    }

    private double find(int A[], int B[], int k, int aStart,
     		int aEnd, int bStart, int bEnd){
    	int aLen = aEnd - aStart + 1;
    	int bLen = bEnd - bStart + 1;
    	if (aLen == 0){
    		return B[bStart + k - 1];
    	}else if (bLen == 0){
    		return A[aStart + k - 1];
    	}else if (k == 1){
    		return Math.min(A[aStart], B[bStart]);
    	}
    	int at = Math.max((aLen * k)/(aLen + bLen), 1);
    	int bt = k - at;
    	int aMid = at + aStart - 1;
    	int bMid = bt + bStart - 1;
    	//System.out.println("aStart: " + aStart + " aEnd: " + aEnd + " bStart: " + bStart + " bEnd: " + bEnd);
        if (A[aMid] < B[bMid]){
    		k = k - at;
    		aStart = aMid + 1;
    		bEnd = bMid;
    	}else{
    		k = k - bt;
    		bStart = bMid + 1; 
    		aEnd = aMid;
    	}
    	return find(A, B, k, aStart, aEnd, bStart, bEnd);
    }
}