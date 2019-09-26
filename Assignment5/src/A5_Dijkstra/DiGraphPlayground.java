package A5_Dijkstra;

public class DiGraphPlayground {

	public static void main (String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like 
		//    -- print
		//    -- sort
		//    -- random fill
		//    -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		exTest();
	}

	public static void exTest(){
		long i = Long.MAX_VALUE;
		System.out.println(i);
		DiGraph d = new DiGraph();
		d.addNode(1, "f");
		d.addNode(2, "s");
		d.addNode(3, "t");	
		d.addNode(4, "fo");
		d.addNode(5, "fi");
		d.addNode(6, "si");
		d.addEdge(0, "f", "s", 0, null);
		d.addEdge(1, "f", "si", 0, null);
		d.addEdge(2, "s", "t", 0, null);
		d.addEdge(3, "fo", "fi", 0, null);
		d.addEdge(4, "fi", "si", 0, null);
		d.addNode(7, "f");
		d.addNode(8, "x");
		d.delNode("f");
		d.addNode(7, "f");
		d.addEdge(5, "f", "x", 0, null);
		d.addEdge(6, "s", "x", 0, null);
		d.addEdge(7, "t", "x", 0, null);
		d.delNode("x");
		d.addNode(9, "x");
		d.addEdge(10, "s", "x", 0, null);
		d.addEdge(11, "t", "x", 0, null);
		d.addEdge(5, "fo", "x", 0, null);
		System.out.println("numEdges: "+d.numEdges());
		System.out.println("numNodes: "+d.numNodes());
		d.print();
	}
}
