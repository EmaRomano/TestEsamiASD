package esame2022_10;

import utilita.TreeNode;
import utilita.Utilities;

public class Driver {
	
	//slave soluzione
	static int cancellaPerPosizione(TreeNode P, TreeNode T, int k, int pos) {
		if(T!=null) {
			pos = cancellaPerPosizione(T, T.left, k, pos);
			pos++;
			if(pos%k==0) T=Utilities.cancellaRadice(P, T);
			pos = cancellaPerPosizione(T, T.right, k, pos);
		}
		return pos;
	}

	//master soluzione
	static TreeNode cancellaPerPosizione(TreeNode T, int k) {
		cancellaPerPosizione(null, T, k, 0);
		return T;
	}

	//Test
	public static void main(String[] args) {
		int[] arr= {8,4,12,3,6,10,14,2,1,5,7,9,11,13,15};

		TreeNode treeABR=Utilities.addArrayOfKeys(null, arr);

		Utilities.printBinaryTree(treeABR);
		
		int k=5;

		System.out.println("\n\ncancellazione dei nodi con posizione multipla di "
				+ k +" nell'ordinamento: \n");

		Utilities.printBinaryTree(cancellaPerPosizione(treeABR, k));

	}	

}


