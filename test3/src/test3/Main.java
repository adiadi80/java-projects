package test3;

public class Main {

	
	
	public static void main(String[] args){
		subsets(5,3);
		}
	
	
	public static void subsets(int n, int k) {
		subsets(n, k, "");
		}
		public static void subsets(int n, int k, String s) {
		if (k == 0)
		System.out.println(s);
		else if (n > 0) {
		subsets(n - 1, k, s);
		subsets(n - 1, k - 1, n + s);
		}
		}
}



