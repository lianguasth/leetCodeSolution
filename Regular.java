import java.util.*;
public class Regular{
	public class Tuple{
		public char s;
		public boolean b;
		public Tuple(char ss, boolean bb){
			this.s = ss;
			this.b = bb;
		}
	}

	public class Point{
		public int pStr;
		public int pPat;
		public Point(int s, int p){
			this.pStr = s;
			this.pPat = p;
		}

		@Override
		public int hashCode(){
			return (this.pStr*31) + this.pPat*41;
		}

		@Override
		public boolean equals(Object obj){
			if (!(obj instanceof Point))
            	return false;
            Point e = (Point)obj;
			return e.pStr == this.pStr && e.pPat == this.pPat;
		}
	}

	private Map<Point, Boolean> set;

	public boolean isMatch(String s, String p){
		int ind = 0;
		int n = p.length();
		int cnt = 0;
		Tuple t[] = new Tuple[p.length()];
		set = new HashMap<Point, Boolean>();
		//tuple
		while (ind < n){
			if (ind + 1 < n && p.charAt(ind + 1) == '*'){
				t[cnt] = new Tuple(p.charAt(ind), true);
				ind += 2;
			}else{
				t[cnt] = new Tuple(p.charAt(ind), false);
				ind += 1;
			}
			cnt += 1;
		}
		//doing recursively
		return _match(s, t, cnt, new Point(0,0));
	}

	public boolean _match(String s, Tuple sm[], int cnt, Point p){
		int pStr = p.pStr;
		int pPat = p.pPat;
		boolean flag;
		// System.out.println("pStr " + pStr +"\t pat: " + pPat);
		if (set.containsKey(p)) {
			// System.out.println("hit");
			return set.get(p);
		}
		if (pPat >= cnt){
			flag = (pStr >= s.length());
		}else if(pStr >= s.length()){
			flag = true;
			while (pPat < cnt){
				if (!sm[pPat].b){
					flag = false;
					break;
				}
				pPat += 1;
			}
		}else if ((sm[pPat].s == s.charAt(pStr)) || sm[pPat].s == '.'){
			//match
			if (sm[pPat].b){
				flag = _match(s, sm ,cnt, new Point(pStr+1, pPat))
					|| _match(s, sm ,cnt, new Point(pStr, pPat+1))
					||_match(s, sm ,cnt, new Point(pStr+1, pPat+1));
			}else{
				flag = _match(s, sm ,cnt, new Point(pStr+1, pPat+1));
			}
		}else if(sm[pPat].b){
			flag = _match(s, sm, cnt, new Point(pStr, pPat+1)); 
		}else{
			flag = false;
		}
		set.put(p, flag);
		/*
		for(Point pp : set.keySet()){
			System.out.print("key: " + pp.pStr + " key2: " + pp.pPat + "\t");
		}
		System.out.println("");
		*/
		return flag;
	}
}