class Solution2 {
    /*
     * param a: The first integer
     * param b: The second integer
     * return: The sum of a and b
     */
    public int aplusb(int a, int b) {
        // write your code here, try to do it without arithmetic operators.
        int res = 0;
        int curRes = 0;
        int carry = 0;
        for (int i = 0; i < 32; i++){
            int ta = (a>>i) &1;
            int tb = (b>>i) &1;
            curRes = ta ^ tb ^ carry;
            res = (curRes << i) | res;
            carry = (ta & tb) | (tb & carry) | (carry & ta);
        }
        return res;
    }
    
    
    public static void main(String argv[]){
    	Solution2 s = new Solution2();
    	System.out.println(s.aplusb(-1, 1));
    }
};