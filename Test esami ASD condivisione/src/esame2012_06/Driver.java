package esame2012_06;

import java.util.LinkedList;
import java.util.Queue;

//unico malfunzionamento riscontrato: nel caso si debbano cancellare tutti i nodi, 
//non cancella la radice (non dipende da cancellaRadice, ma dalla soluzione)

public class Driver {
	
	//algoritmo pezzotto per costruire l'albero
	static TreeNode buildTreeFromArray(int[] arrayOrdinato) {
		Queue<TreeNode> q = new LinkedList<>();
		TreeNode T=new TreeNode(arrayOrdinato[0]);
		q.add(T);
		int len = arrayOrdinato.length-1;
		int count=1;
		while(count<len) {
			TreeNode P=q.element();
			if(P.left==null) {
				P.left=new TreeNode(arrayOrdinato[count++]);
				q.add(P.left);
			}
			if(P.right==null) {
				P.right=new TreeNode(arrayOrdinato[count++]);
				q.add(P.right);
			}
			if(P.left!=null && P.right!=null)
				q.poll();
		}
		
		return T;
	}

	static void scambiaChiavi(TreeNode T1, TreeNode T2) {
		if (T1!=null && T2!=null) {
			int tmp = T1.key;
			T1.key = T2.key;
			T2.key = tmp;
		}
	}
	
	static TreeNode cancellaRadice(TreeNode P, TreeNode T) {
		if(T!=null) {
			if(T.left!=null || T.right!= null) {
				if(T.left==null || T.right!=null && T.left.key>T.right.key) { 
					scambiaChiavi(T, T.right);
					T.right=cancellaRadice(T, T.right);
					}
				else if(T.right==null || T.left.key<T.right.key) { 
					scambiaChiavi(T, T.left);
					T.left=cancellaRadice(T, T.left);
				}
				
			}else {
				if(P!=null){
				   if(P.left==T) P.left=null;
				   else P.right=null;	
				     } 
				return null;
		        }
		}
	
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
	
	static int algo(TreeNode P, TreeNode T, int k1, int k2, int x, int n) {
		if(T!=null) {
			int sx=algo(T, T.left, k1, k2, x, n);
			int dx=algo(T, T.right, k1, k2, x, n);
			n=sx+dx;
			if(T.key<=k2 && T.key>=k1) {
				n++;
				if(n<=x) T=cancellaRadice(P, T);
			}
			
		}
		return n;
	}
	
	static TreeNode soluzione(TreeNode T, int k1, int k2, int x) {
		algo(null, T, k1, k2, x, 0);
		return T;
	}



	//Test
	public static void main(String[] args) {
		//perche' l'algoritmo di inserimento funzioni, e' necessario che l'array
		//sia ordinato in modo crescente
		int[] arr= {1,2,3,4,5,6,7,8,9,10};

		TreeNode T=buildTreeFromArray(arr);
		printBinaryTree(T);
		
		System.out.print("\n\n\n");
		
		int k1=1, k2=10, x=12;
		
		System.out.println("cancellazione dei nodi tali che:\n"
				+ "-- abbiano chiave compresa tra "+k1+" e "+k2+" (estremi inclusi)\n"
				+ "-- siano radici di un sottoalbero con un numero di chiavi "
				+ "comprese tra "+k1+" e "+k2+" minore o uguale a "+x + " (radice compresa)\n\n");

		printBinaryTree(soluzione(T, k1, k2, x));

	}

}
