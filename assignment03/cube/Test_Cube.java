public class Test_Cube {
	public static void main(String[] args) {
		Assignment3_Cube cube1 = new Assignment3_Cube("cube_input2.txt");
		for(int i = 1; i <= 6; i++) {
			System.out.println(i);
			int[][] t = cube1.get(i);
			for(int j = 0; j < t.length; j++) {
				for(int k = 0; k < t[j].length; k++)
					System.out.print(t[j][k] + " ");
				System.out.println();
			}
		}
	}
}