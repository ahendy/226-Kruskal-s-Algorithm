/* MWST.java
   CSC 226 - Fall 2015
   Assignment 1 - Minimum Weight Spanning Tree Template
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java MWST
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java MWST file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry A[i][j] of the adjacency matrix gives the weight of the edge from 
   vertex i to vertex j (if A[i][j] is 0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that A[i][j]
   is always equal to A[j][i].
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.


   B. Bird - 08/02/2014
*/

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

//Do not change the name of the MWST class
public class MWST{

	/* mwst(G)
		Given an adjacency matrix for graph G, return the total weight
		of all edges in a minimum weight spanning tree.
		
		If G[i][j] == 0, there is no edge between vertex i and vertex j
		If G[i][j] > 0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	static int MWST(int[][] G){
		int numVerts = G.length;
		ListNode toSort = new ListNode(new Edge(0,0,0));
		ListNode p = toSort;;
		int totalWeight = 0;
		

		int sum=0;

		int count2 = 0; //save pos for diagonal traversal
		for(int row = 0; row <numVerts;row++){
			count2++;		//each time we travel down a row, begin column by 1
			for(int column=count2; column< numVerts;column++){
				
				if(G[row][column]>0){
					p.next = new ListNode(new Edge(row,column, G[row][column]));		//push to ListNode
					p = p.next;
					
				}
				
			}
			
			
		}
		
		System.out.println("sum: " +sum);
		//toSort = toSort.nexxt; //bad code but couldnt figure out how to go around
		//f = null; // other than removing first node created from toSort = newListNode(0)
		//for(UFDS a: ufds){
			//System.out.print("("+ a.x +", "+ a.y+") Weight: " +a.weight);


		//}
	 	toSort = removeFront(toSort);	
		//

		ListNode sorted = MergeSort(toSort); //sort 
		
		printList(sorted);
		//sorted now contains a  sorted ListNode of edge objects.
	ListNode sorted2 = sorted;
	
	while(sorted!= null){
		Edge a = sorted.value;
		int leastEdge = a.weight;
		//System.out.println("least edge: "+ leastEdge);
		//System.out.print(" "+ edgeVec.size()+" "+ listLength(sorted));
		//printList(sorted);
		//sorted = removeFront(sorted);
		sorted = sorted.next;
		//for(Edge a: edgeVec){
			//if(a.weight == leastEdge){
				//a.visited = true;
				//edgeVec.remove(a);
		//if(!cycleTest(a)){
			
			//System.out.println(a.x.val);
			//System.out.println("a.parent.valTWO: "+ a.x.parent.val+ " b.parent.valTWO "+ a.y.parent.val+"\n");

		totalWeight+= union(a.x,a.y,leastEdge); 
		System.out.println("a.x.parent.val"+ findSet(a.x).parent.val);
		//}
	//	System.out.println(totalWeight);	//break;
	
		//System.out.println("before2 "+ findSet(a.x).parent.val);
		
		
		//findSet(a.x).parent = new Vert(69999);
		//System.out.println(findSet(a.x).val+ " after2 "+findSet(a.x).parent.val);

			//}




		//}

	}
	


		
		return totalWeight;
		
	}
	public static boolean cycleTest(Edge a){
		if(findSet(a.x)!=findSet(a.y)){
			
			return false;

		}
		return true;



	}

	public static Vert findSet(Vert a){
		if(a.parent != a){
			//System.out.println("parent: "+a.parent.val +" current: "+ a.val +" ");
			
			a.parent = findSet(a.parent);
			System.out.print(a.val+ " ");
		}

		return a.parent;


	}
	public static int union(Vert xr, Vert yr, int leastEdge){
		Vert a = findSet(xr);
		Vert b = findSet(yr);
		Vert c = new Vert(69);
	/*	if(a.val==3){
			
			
		}
		else if(a.rank>b.rank){
			b.parent = a;
			System.out.println("b.val: "+b.val);
			
		} else if (a.rank<b.rank){
			a.parent = b;


		}else if (a.rank == b.rank){
			a.parent = b;
			b.rank ++;

		}

	*/	
		System.out.println("a.val: "+a.val+" a.parent.val: "+ a.parent.parent.val+ " b.parent.val "+ b.parent.val +"\n");
		a.parent = b;
		
		return leastEdge;

		
	}

	

	/*public static boolean cycleTest(UFDS t){ //true if cycle false if no cycle
		if(t.forest.contains(t.x) && t.forest.contains(t.y)){
			return true;

		}if(!t.forest.contains(t.x)) {
				t.forest.add(t.x);
				System.out.println("adding t.x: "+ t.x);

		}if(!t.forest.contains(t.y)){
				t.forest.add(t.y);
				System.out.println("adding t.y: "+ t.y);	
		}
				
		return false;		

	}*/

	public static ListNode removeFront(ListNode head){
		ListNode f = head;
		head = head.next;
		f = null;
		return head;


	}

	public static int listLength(ListNode node){
		if (node == null)
			return 0;
		return 1 + listLength(node.next);
	}

	public static ListNode addBack(ListNode head, ListNode value){
		ListNode sorted;
		if(value == null){
			return head;
		}
		if(head == null){
			return value;

		}
		if ( head.value.weight< value.value.weight){
			sorted = new ListNode( head.value, addBack(head.next,value));
			//sorted.next = addBack(head.next, value);
			//return sorted;
		}
		else{
			sorted = new ListNode( value.value, addBack(head,value.next));
			//sorted.next = addBack(head,value.next);
			//return sorted;

		}
		return sorted;
		//rfeturn addBack(head.next, value);

	}


	public static ListNode findMid(ListNode head,ListNode end){
		ListNode mid = head;
		
		
		if (end == null||end.next == null||end.next.next==null){ //end.next.next is required or else null pointers would happen on a few cases.
			return mid;														// also size n  = 4, middle pointer would be too far.
		}
		end = end.next;
		return findMid(head.next,end.next);			// recursively increment back pointer by 2, front by one making front pointer be at middle.

	}

	public static ListNode MergeSort(ListNode head){
		

		if(listLength(head)<=1 || head ==null ){

			return head;
		}
		
		ListNode middle = findMid(head,head); 
		ListNode tail = middle.next; //break from middle pointer.next to make tail		
		middle.next = null; //middle pointer edits head linked list, breaking it into head nodes
		
		ListNode left =  MergeSort(head); //recursively create lists of size one
		ListNode right = MergeSort(tail); //by continuously splitting in half by findMid



		//addBack(head,end);

		//addBack(head, end); //addback WORKS
		//return end;
		return addBack(left,right);   //originally was listMerge, edited my add to back function to create a sort and merge function
	}
	




	public static void printList(ListNode node){
		if (node == null)
			System.out.println();
		else{
			System.out.print(node.value.weight + " ");
			printList(node.next);
		}
	}
	public static class ListNode{
		Edge value;
		ListNode next;
		public ListNode(){
			
			next = null;
		}
		public ListNode(Edge value){
			this.value = value;
			this.next = null;
		}
		public ListNode(Edge value, ListNode next){
			this.value = value;
			this.next = next;
		}
	}

	public static class Edge{
		public Vert x;
		public Vert y;
		public int weight;
		//public boolean visited = false;
		public Edge(int a,int b,int weight){
			this.weight = weight;
			this.x = new Vert(a);
			this.y = new Vert(b);

			
		}

	}

	public static class Vert{
		public Vert parent= this;
		public int val;
		public int rank= 0;
		public Vert(int val){
			this.val = val;
			//this.parent = this;
			
		}
		public Vert(int val, Vert parent){
			this.val = val;
			this.parent = parent;


		}

	}
		
	/* main()
	   Contains code to test the MWST function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			long startTime = System.currentTimeMillis();
			
			int totalWeight = MWST(G);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			System.out.printf("Graph %d: Total weight is %d\n",graphNum,totalWeight);
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
}
