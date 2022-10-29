package esame2013_02;
import utilita.*;
//versione un po' piu' complicata della traccia uscita in 2016_06
//qui la traccia obbliga ad usare un array
public class Driver {

	//crea un array ordinato dall'albero
	static int[] sortedArrFromBST(TreeNode T) {
		if (T!=null) {
			int n = Utilities.numNodes(T);
			int[] sortedArr = new int[n];		
			sortedArrFromBST(T, sortedArr, 0);
			return sortedArr;
		}
		return null;
	}
	
	static int sortedArrFromBST(TreeNode T, int[] arr, int counter) {
		if(T!=null) {
	        counter=sortedArrFromBST(T.left, arr, counter);
			arr[counter++]=T.key;
            counter=sortedArrFromBST(T.right, arr, counter);
		}
		return counter;
	}
	
	static TreeNode balancedBSTFromArray(TreeNode T, int[] sortedArr, int first, int last) {
		if(first<=last) {
			int mid=(first+last)/2;
			T=Utilities.insertABR(T, sortedArr[mid]);
			T.left=balancedBSTFromArray(T.left, sortedArr, first, mid-1);
			T.right=balancedBSTFromArray(T.right, sortedArr, mid+1, last);
		}
		return T;
	}
	
	static TreeNode balancedBST(TreeNode T) {
		int[] sortedArr=sortedArrFromBST(T);
		return balancedBSTFromArray(null, sortedArr, 0, sortedArr.length-1);
	}
	
	//test
	public static void main(String[] arr) {
		int[] a = {4,2,6,7,12,9,-3,2,-1,1,9,13,0,1,10};
		TreeNode T=Utilities.addArrayOfKeys(null, a);
		Utilities.printBinaryTree(T);
		System.out.print("\n\nalbero bilanciato:\n\n");
		Utilities.printBinaryTree(balancedBST(T));
		
	}

}
