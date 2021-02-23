/* 
 * 
 * Helper class for some functions 
 * */
public class Tools {

	private static int getDifference(int pointA, int pointB) {
		int difference;
		if (pointA > pointB) {
			difference = pointA - pointB;
		} else {
			difference = pointB - pointA;
		}
		return difference;

	}

	public static boolean checkGap(int[] pos1, int[] pos2) {

		int gapX = getDifference(pos1[0], pos2[0]);
		int gapY = getDifference(pos1[1], pos2[1]);

		if (gapX > 2 && gapY > 2) {
			return true;
		} else {
			return false;
		}
	}
}
