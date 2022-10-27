package esame2022_10;
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

	//overloading: versione che salva anche il padre per evitare alcuni tipi di problemi 
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

	//slave soluzione
	static int cancellaPerPosizione(TreeNode P, TreeNode T, int k, int pos) {
		if(T!=null) {
			pos = cancellaPerPosizione(T, T.left, k, pos);
			pos++;
			if(pos%k==0) T=cancellaRadice(P, T);
			//cosa succede se qui uso la versione originale di cancellaRadice?...
			pos = cancellaPerPosizione(T, T.right, k, pos);
		}
		return pos;
	}

	//master soluzione
	static TreeNode cancellaPerPosizione(TreeNode T, int k) {
		cancellaPerPosizione(null, T, k, 0);
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

		System.out.println("\n\n\n");

		printBinaryTree(cancellaPerPosizione(treeABR, 5));

	}	

}


