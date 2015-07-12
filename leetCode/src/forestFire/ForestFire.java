package forestFire;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ForestFire {
	static int nTree = 50;
	static Random rand = new Random();
	int qFlag = 0;
	static int forest[][] = new int[nTree][nTree];
	{
		rand.setSeed(15826);
	}

	// generate the forest with probobility tree
	private static void genForest(float prob) {
		rand.setSeed(15826);
		for (int i = 0; i < nTree; i++) {
			for (int j = 0; j < nTree; j++) {
				if (rand.nextFloat() <= prob) {
					forest[i][j] = 1;
				} else {
					forest[i][j] = 0;
				}
			}
		}
	}

	private Pair chooseTheSite() {
		int i, j;
		do {
			i = rand.nextInt(50);
			j = rand.nextInt(50);
		} while (forest[i][j] != 1);
		Pair p = new Pair(i, j);
		return p;
	}

	private class Pair {
		public int x;
		public int y;

		Pair(int aa, int bb) {
			x = aa;
			y = bb;
		}

		@Override
		public boolean equals(Object p) {
			if (this.getClass() != p.getClass()) {
				return false;
			}
			return ((Pair) p).x == x && ((Pair) p).y == y;
		}

		@Override
		public String toString() {
			return "x:" + x + "y" + y;
		}
	}

	// to those that exsits in the list, do not add it to list
	private ArrayList<Pair> addPoints(Pair p, ArrayList<Pair> list) {
		int x = p.x;
		int y = p.y;
		ArrayList<Pair> li = new ArrayList<Pair>();
		if (goodPair(x - 1, y, list)) {
			li.add(new Pair(x - 1, y));
		}
		if (goodPair(x + 1, y, list)) {
			li.add(new Pair(x + 1, y));
		}
		if (goodPair(x, y - 1, list)) {
			li.add(new Pair(x, y - 1));
		}
		if (goodPair(x, y + 1, list)) {
			li.add(new Pair(x, y + 1));
		}
		return li;
	}

	private boolean goodPair(int x, int y, ArrayList<Pair> list) {
		return (x >= 0 && x <= 49 && y >= 0 && y <= 49 && forest[x][y] == 1 && !list
				.contains(new Pair(x, y)));
	}

	public static void main(String[] args) throws IOException {
		// arg 0 rmin
		// arg 1 rmax
		// arg 2 qFlag
		ForestFire f = new ForestFire();
		f.cal(args);
	}

	private boolean legalPair(int x, int y) {
		return (x >= 0 && x <= 49 && y >= 0 && y <= 49 && forest[x][y] == 1);
	}

	private void setZero(int x, int y) {
		this.tmpCount++;
		if (legalPair(x + 1, y)) {
			forest[x + 1][y] = 0;
			setZero(x + 1, y);
		}
		if (legalPair(x - 1, y)) {
			forest[x - 1][y] = 0;
			setZero(x - 1, y);
		}
		if (legalPair(x, y + 1)) {
			forest[x][y + 1] = 0;
			setZero(x, y + 1);
		}
		if (legalPair(x, y - 1)) {
			forest[x][y - 1] = 0;
			setZero(x, y - 1);
		}
	}

	private int tmpCount = 0;

	private void saveMap(float p, String name) throws IOException {
		Writer r = getWriter(name + ".txt");
		Writer r2 = getWriter(name + "clusters.txt");
		genForest(p);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				r.write(forest[i][j] + "\t");
			}
			r.write("\n");
		}
		// calculate the clusters using brutal ways
		int count = 0;
		HashMap<Integer, Integer> sizeCount = new HashMap<Integer, Integer>();

		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (forest[i][j] == 1) {
					this.tmpCount = 0;
					setZero(i, j);
					System.out.println(tmpCount);
					if (sizeCount.containsKey(tmpCount)) {
						sizeCount.put(tmpCount, sizeCount.get(tmpCount) + 1);
					} else {
						sizeCount.put(tmpCount, 1);
					}
				}
			}
		}
		for (Integer iter : sizeCount.keySet()){
			r2.write(" " + iter + "\t" + sizeCount.get(iter) + "\n");
		}
		
		System.out.println("count is :" + count);
		r.close();
		r2.close();
	}

	private Writer getWriter(String name) throws IOException {
		File yourFile = new File(name);
		if (!yourFile.exists()) {
			yourFile.createNewFile();
		}
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				yourFile), "utf-8"));
	}

	private void cal(String[] args) throws IOException {
		float rmin = Float.parseFloat(args[0]);
		float rmax = Float.parseFloat(args[1]);
		int iter = (int) ((rmax - rmin) / 0.05) + 1;
		// initialize the writers
		Writer writerQ1, writerQ2;
		writerQ1 = getWriter("q1.txt");
		writerQ2 = getWriter("q2.txt");

		float stepRecord[] = new float[iter];
		for (int i = 0; i < iter; i++) {
			int maxStep = 0;
			for (int j = 0; j < 5; j++) {
				// generate the forest
				int step = 0;
				genForest((float) (rmin + i * 0.05));
				rand = new Random(System.currentTimeMillis());
				Pair initP = chooseTheSite();
				// lets build a stack to handle this
				ArrayList<Pair> lists = new ArrayList<Pair>();
				lists.add(initP);

				while (lists.size() != 0) {
					int num = lists.size();
					// System.out.println(lists.get(0));
					for (int i1 = 0; i1 < num; i1++) {
						Pair tp = lists.get(i1);
						forest[tp.x][tp.y] = 0;
						ArrayList<Pair> li = addPoints(tp, lists);
						// System.out.println("the size of li:" + li.size());
						lists.addAll(li);
					}
					lists.removeAll(lists.subList(0, num));
					step++;
				}
				if (step > maxStep) {
					stepRecord[i] = step;
					maxStep = step;
				}
				// print the step
				writerQ1.write(step + "\t");
			}
			// 1
			writerQ1.write("\n");
		}
		for (int i = 0; i < iter; i++) {
			// 2
			writerQ2.write(" " + new Float(0.05 * i + rmin) + "\t"
					+ stepRecord[i] + "\n");
		}

		// 3
		saveMap(0.10f, "q3_0_1");
		saveMap(0.90f, "q3_0_9");
		saveMap(0.60f, "q3_0_best");
		System.out.println("job done..");
		writerQ1.close();
		writerQ2.close();
	}
}
