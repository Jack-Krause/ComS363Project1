package project1_3;

public class TestingHelper {
	
	
	public static boolean checkExecuteBatch(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				System.out.println("Execute Batch err: " + arr[i]);
				return false;
			}
		}
		
		System.out.println("batch returns 0s\n");
		return true;
	}

}
