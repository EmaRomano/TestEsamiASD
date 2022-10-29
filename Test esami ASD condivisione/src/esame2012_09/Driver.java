package esame2012_09;
import utilita.TreeNode;
import utilita.Utilities;

public class Driver {

	//soluzione, slave
	static int algo(TreeNode P, TreeNode T, int h, int h1, int h2, int n, int n1, int n2, int k) {
		if(T!=null) {
			h++;
			if (T.key>=k) algo(T, T.left, h, h1, h2, n, n1, n2, k);
			else{
				int sx = algo(T, T.left, h, h1, h2, n, n1, n2, k);
				int dx = algo(T, T.right, h, h1, h2, n, n1, n2, k);
				n = sx + dx + 1;
				if (T.key%2==0 && Utilities.isBetween(h1, h, h2) && Utilities.isBetween(n1, n, n2))
					T = Utilities.cancellaRadice(P, T);
			}
		}

		return n;
	}

	//soluzione, master
	static TreeNode soluzione(TreeNode T, int h1, int h2, int n1, int n2, int k) {
		algo(null, T, -1, h1, h2, 0, n1, n2, k);
		return T;
	}


	//Test
	public static void main(String[] args) {
		int[] arr= {8,4,12,3,6,10,14,2,1,5,7,9,11,13,15};

		TreeNode treeABR=Utilities.addArrayOfKeys(null, arr);

		Utilities.printBinaryTree(treeABR);

		System.out.println("\n\n");
		
		int h1=0, h2=2, n1=1, n2=9, k=10;
		
		System.out.println("cancellazione dei nodi tali che:\n"
				+ "-- abbiano chiave pari e strettamente minore di " + k +"\n" 
				+"-- abbiano distanza dalla radice compresa tra "+h1+" e "+h2+"\n"
				+ "-- siano radici di un sottoalbero con un numero di chiavi strettamente minori di "+k
				+ " compreso tra "+n1+" e "+n2+" (radice compresa);\n\n");

		Utilities.printBinaryTree(soluzione(treeABR, h1, h2, n1, n2 ,k));

	}

}
