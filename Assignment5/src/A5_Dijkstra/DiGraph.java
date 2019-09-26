package A5_Dijkstra;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class DiGraph implements DiGraph_Interface {

	private Map<String, Node> nodeMap = new HashMap<String, Node>();
	private Set<Long> eIds = new HashSet<Long>();
	private Set<Long> nIds = new HashSet<Long>();
	private Map<String, Set<String>> dLabelSet = new HashMap<String, Set<String>>();
	private long numNodes = 0;
	private long numEdges = 0;
	public DiGraph () {} 
	@Override
	public boolean addNode(long idNum, String label) {
		if(idNum<0 || nIds.contains(idNum) || label == null || nodeMap.containsKey(label))
			return false;
		nodeMap.put(label, new Node(idNum, label));
		nIds.add(idNum);
		numNodes++;
		return true;
	}
	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (idNum < 0 || sLabel == null || dLabel == null || eIds.contains(idNum) || 
				!(nodeMap.containsKey(sLabel) && nodeMap.containsKey(dLabel)) || 
				nodeMap.get(sLabel).containOutEdge(dLabel))
			return false;
		nodeMap.get(sLabel).addOutEdge(dLabel, new Edge(idNum, sLabel, dLabel, weight, eLabel));
		eIds.add(idNum);
		numEdges++;
		if(dLabelSet.containsKey(dLabel))
			dLabelSet.get(dLabel).add(sLabel);
		else
		{
			Set<String> sourceLabel = new HashSet<String>();
			sourceLabel.add(sLabel);
			dLabelSet.put(dLabel,sourceLabel);
		}
		return true;
	}
	@Override
	public boolean delNode(String label) {
		if(!nodeMap.containsKey(label))
			return false;
		for(Edge edge: nodeMap.get(label).getAllOutEdges()){
			eIds.remove(edge.getIdNum());
			numEdges--;
		}
		if(dLabelSet.containsKey(label))
		{
			for(String source: dLabelSet.get(label))
			{
				delEdge(source, label);
			}
			dLabelSet.get(label).clear();
		}
		nIds.remove((nodeMap.get(label).getId()));
		nodeMap.remove(label);
		numNodes--;
		return true;
	}
	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if(!(nodeMap.containsKey(sLabel) && nodeMap.containsKey(dLabel)) ||
				!nodeMap.get(sLabel).containOutEdge(dLabel)) 
			return false;
		eIds.remove(nodeMap.get(sLabel).getOutEdge(dLabel).getIdNum());
		nodeMap.get(sLabel).removeOutEdge(dLabel);
		numEdges--;
		dLabelSet.remove(dLabel, sLabel);
		return true;
	}
	@Override
	public long numNodes() {
		return numNodes;
	}
	@Override
	public long numEdges() {
		return numEdges;
	}
	@Override
	public ShortestPathInfo_Interface[] shortestPath(String label) {
		ShortestPathInfo_Interface[] sp = new ShortestPathInfo[(int) numNodes];
		MinBinHeap pq = new MinBinHeap();
		MinBinHeap pq2 = new MinBinHeap();
		for(Node n: nodeMap.values())
		{
			n.setDv(Long.MAX_VALUE);
		}
		nodeMap.get(label).setDv(0);
		pq.insert(new EntryPair(label,0));
		while(pq.size()>0)
		{
			Node n = nodeMap.get(pq.getMin().getValue());
			pq.delMin();
			for(Edge e: n.getAllOutEdges())
			{
				long newW = n.getDv() + e.getWeight();
				if(newW < nodeMap.get(e.getdLabel()).getDv())
				{
					nodeMap.get(e.getdLabel()).setDv(newW);
					pq.insert(new EntryPair(nodeMap.get(e.getdLabel()).getLabel(),(int) newW));
				}
			}
			pq2.insert(new EntryPair(n.getLabel(),(int) n.getDv()));
		}
		int i = 0;
		for(Node n: nodeMap.values())
		{
			if(n.getDv()==Long.MAX_VALUE)
				sp[i] = new ShortestPathInfo(n.getLabel(),-1);
			else
				sp[i] = new ShortestPathInfo(n.getLabel(),n.getDv());
			i++;
		}
		return sp;
	}
	public void print(){

		for(String key: nodeMap.keySet()){
			Node n = nodeMap.get(key);

			System.out.println("("+ n.getId() + ")" + n.getLabel());

			for(Edge e: n.getAllOutEdges()){
				System.out.println("	(" + e.getIdNum() + ")__" + ((e.geteLabel() == null) ? e.getWeight(): e.geteLabel() + "," + e.getWeight()) + "__> " + e.getdLabel());
			}
		}

	}
}