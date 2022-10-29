package utilita;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
	
	//algoritmo di inserimento visto a lezione
	public static TreeNode insertABR(TreeNode T, int k) {
		if(T==null) {
			T=new TreeNode();
			T.key=k;}
		else if(T.key<k)
			T.right=insertABR(T.right, k);
		else if(T.key>k)
			T.left=insertABR(T.left, k);

		return T;
	}
	
	//algoritmo che inserisce in un ABR tutte le chiavi in un array
	public static TreeNode addArrayOfKeys(TreeNode T, int[] arr) {
		for(int i=0; i<arr.length; i++) {
			T=insertABR(T, arr[i]);
		}
		return T;
	}

	//algoritmi per cancellazione
	public static TreeNode staccaMassimo(TreeNode T, TreeNode P) {
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

	//algoritmo di cancellazione visto a lezione
	public static TreeNode cancellaRadice(TreeNode T) {
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

	//overloading: algoritmo di cancellazione che salva anche il padre per evitare alcuni problemi 
	public static TreeNode cancellaRadice(TreeNode P, TreeNode T) {
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
	
	//restituisce una lista ordinata (crescente) contenente le chiavi di un ABR
	public static List<Integer> insertInArray(TreeNode T, List<Integer> sortedList){
		if(T==null)
			return sortedList;
		else {
			sortedList=insertInArray(T.left, sortedList);
			if(sortedList==null) sortedList=new ArrayList<>();
			sortedList.add(T.key);
			sortedList=insertInArray(T.right, sortedList);
			return sortedList;
		}	
	}
	
	//inserisce le chiavi di una lista ordinata (crescente) in un ABR in modo ottimale
	public static TreeNode insertSortedListInBST(List<Integer> sortedList, TreeNode T, int p, int r) {
		if(p<=r) {
			int mid=(p+r)/2;
			T=insertABR(T, sortedList.get(mid));
			T.left=insertSortedListInBST(sortedList, T.left, p, mid-1);
			T.right=insertSortedListInBST(sortedList, T.right, mid+1, r);
		}
		return T;
	}
	
	//Trasforma un ABR in uno bilanciato (senza usare campo altezza nei nodi)
	public static TreeNode balanceBST(TreeNode root) {
		List<Integer> sortedList=new ArrayList<Integer>();
		sortedList=insertInArray(root, sortedList);
		return insertSortedListInBST(sortedList, null, 0, sortedList.size()-1);
	}
	
	//costruisce un ABR bilanciato con le chiavi contenute in un array
	public static TreeNode balancedBSTFromArray(int[] arr) {
		TreeNode T= balanceBST(addArrayOfKeys(null, arr));
		return T;
	}

	public static boolean isBetween(int a, int b, int c) {
		return a<=b && b<=c;
	}
	
	public static void scambiaChiavi(TreeNode T1, TreeNode T2) {
		if (T1!=null && T2!=null) {
			int tmp = T1.key;
			T1.key = T2.key;
			T2.key = tmp;
		}
	}
	
	//numero di nodi in un albero binario (generico)
	public static int numNodes(TreeNode T) {
		if(T!=null) return 1+numNodes(T.left)+numNodes(T.right);
		return 0;
	}
	
	//altezza di un albero binario (generico)
	public static int highness(TreeNode T) {
		if(T!=null) return 1+Math.max(highness(T.left), highness(T.right));
		return -1;
	}
	
	
	//stampa alberi binari (qualsiasi)
	public static void printBinaryTree(TreeNode T, int h) {
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
	
	public static void printBinaryTree(TreeNode T) {
		printBinaryTree(T, 0);
	}
	
	
	
	
//	//test
//	public static void main(String[] args) {
//		int [] arr= {4,5,7,8,9,29,0,1,3,4,5,6,7,8,9,0,12,13,14,15,16,17};
//		printBinaryTree(balancedBSTFromArray(arr));
//	}

}
