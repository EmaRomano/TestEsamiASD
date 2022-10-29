package esame2016_06;
import utilita.TreeNode;
import utilita.Utilities;
//questa traccia non e' altro che una versione piu' semplice di quella uscita in 2013_02
public class Driver {
	
	static TreeNode insertArr(TreeNode T, int[] sortedArr, int first, int last) {
		if(first<=last) {
			int mid=(first+last)/2;
			T=Utilities.insertABR(T, sortedArr[mid]);
			T.left=insertArr(T.left, sortedArr, first, mid-1);
			T.right=insertArr(T.right, sortedArr, mid+1, last);		
		}
		return T;
	}
	
	static TreeNode solution(TreeNode T, int[] sortedArr) {
		return insertArr(T, sortedArr, 0, sortedArr.length-1);
	}
	
	//test
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		Utilities.printBinaryTree(solution(null, arr));
	}


}
