package A5_Dijkstra;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Node {
	private long id;
	private String label;
	private Map<String, Edge> outEdge = new HashMap<String, Edge>();
	private boolean known;
	private long dv;
	private Node pv;

	public Node(long id, String label)
	{
		dv= Long.MAX_VALUE;
		this.setId(id);
		this.setLabel(label);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public boolean containOutEdge(String dLabel)
	{
		return outEdge.containsKey(dLabel);
	}

	public Edge getOutEdge(String dLabel)
	{
		return outEdge.get(dLabel);
	}
	public void addOutEdge(String dLabel, Edge Edge){
		outEdge.put(dLabel, Edge);
	}

	public void removeOutEdge(String dLabel){
		outEdge.remove(dLabel);
	}
	
	public Collection<Edge> getAllOutEdges()
	{
		return outEdge.values();
	}
	public boolean isKnown() {
		return known;
	}
	public void setKnown(boolean known) {
		this.known = known;
	}
	public long getDv() {
		return dv;
	}
	public void setDv(long dv) {
		this.dv = dv;
	}
	public Node getPv() {
		return pv;
	}
	public void setPv(Node pv) {
		this.pv = pv;
	}
}
