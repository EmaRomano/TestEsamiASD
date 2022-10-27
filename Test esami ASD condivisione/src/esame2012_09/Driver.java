package esame2012_09;

//esercizio prova esame 


public class Driver {

	//algoritmi di inserimento (per testing)
	static TreeNode addArrayOfKeys(TreeNode T, int[] arr) {
		for(int i=0; i<arr.length; i++) {
			T=insertABR(T, arr[i]);
		}

		return T;
	}

	static TreeNode insertABR(TreeNode T, int k) {
		if(T==null) {
			T=new TreeNode();
			T.key=k;}
		else if(T.key<k)
			T.right=insertABR(T.right, k);
		else if(T.key>k)
			T.left=insertABR(T.left, k);

		return T;
	}

	//algoritmi per cancellazione
	static TreeNode staccaMassimo(TreeNode T, TreeNode P) {
		if(T!=null) {
			if(T.right!=null) {
				return staccaMassimo(T.right, T);
			}else {
				if(P!=null) {
					if(P.left==T)
						P.left=T.left;
					else
						P.right=T.left;
					return T;
				}
			}
		}
		return T;
	}

	//stessa versione vista a lezione; PER QUESTO ESERCIZIO NON VA BENE, VA AGGIUSTATO. 
	static TreeNode cancellaRadice(TreeNode T) {
		if(T!=null) {
			if(T.left==null || T.right==null) {
				if(T.left!=null) T=T.left;
				else if(T.right!=null)T=T.right;
				else T=null;
			}else {
				T.key=staccaMassimo(T.left, T).key;
			}
		}
		return T;
	}

	//overloading: versione che salva anche il padre per evitare alcuni problemi 
	static TreeNode cancellaRadice(TreeNode P, TreeNode T) {
		if(T!=null) {
			if(T.left==null || T.right==null) {
				if(P==null) {
					if(T.left!=null)T=T.left;
					else T=T.right;
				}else {
					if(P.left==T) {
						if(T.left!=null)P.left=T.left;
						else P.left=T.right;
					}else {
						if(T.left!=null)P.right=T.left;
						else P.right=T.right;
					}
				}
			} else {
				T.key=staccaMassimo(T.left, T).key;
			}
		}
		return T;
	}

	static boolean isBetween(int a, int b, int c) {
		return a<=b && b<=c;
	}

	//soluzione, slave
	static int algo(TreeNode P, TreeNode T, int h, int h1, int h2, int n, int n1, int n2, int k) {
		if(T!=null) {
			h++;
			if (T.key>=k) algo(T, T.left, h, h1, h2, n, n1, n2, k);
			else{
				int sx = algo(T, T.left, h, h1, h2, n, n1, n2, k);
				int dx = algo(T, T.right, h, h1, h2, n, n1, n2, k);
				n = sx + dx + 1;
				if (T.key%2==0 && isBetween(h1, h, h2) && isBetween(n1, n, n2))
					T = cancellaRadice(P, T);
			}
		}

		return n;
	}

	//soluzione, master
	static TreeNode soluzione(TreeNode T, int h1, int h2, int n1, int n2, int k) {
		algo(null, T, -1, h1, h2, 0, n1, n2, k);
		return T;
	}


	//stampa
	static void printBinaryTree(TreeNode T, int h) {
		if(T!=null) {
			h=h+1;
			printBinaryTree(T.right, h);
			for(int i=0; i<h;i++) {
				System.out.print("     ");
			}
			System.out.println(T.key);
			printBinaryTree(T.left, h);
		}
	}

	static void printBinaryTree(TreeNode T) {
		printBinaryTree(T, 0);
	}


	//Test
	public static void main(String[] args) {
		int[] arr= {8,4,12,3,6,10,14,2,1,5,7,9,11,13,15};

		TreeNode treeABR=addArrayOfKeys(null, arr);

		printBinaryTree(treeABR);

		System.out.println("\n\n");
		
		int h1=0, h2=2, n1=1, n2=9, k=10;
		
		System.out.println("cancellazione dei nodi tali che:\n"
				+ "-- abbiano chiave pari e minore di " + k +"\n" 
				+"-- abbiano distanza dalla radice compresa tra "+h1+" e "+h2+"\n"
				+ "-- siano radici di un sottoalbero con un numero di chiavi minori di "+k
				+ " compreso tra "+n1+" e "+n2+" (radice compresa);\n\n");

		printBinaryTree(soluzione(treeABR, h1, h2, n1, n2 ,k));

	}

}
