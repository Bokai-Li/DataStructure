package A5_Dijkstra;

public class Edge {

	private long idNum;
	private String sLabel;
	private String dLabel;
	private String eLabel;
	private long weight;


	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel){

		this.setIdNum(idNum);
		this.setsLabel(sLabel);
		this.setdLabel(dLabel);
		this.setWeight(weight);
		this.seteLabel(eLabel);

	}

	public String getsLabel() {
		return sLabel;
	}

	public void setsLabel(String sLabel) {
		this.sLabel = sLabel;
	}

	public String getdLabel() {
		return dLabel;
	}

	public void setdLabel(String dLabel) {
		this.dLabel = dLabel;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getIdNum() {
		return idNum;
	}

	public void setIdNum(long idNum) {
		this.idNum = idNum;
	}

	public String geteLabel() {
		return eLabel;
	}

	public void seteLabel(String eLabel) {
		this.eLabel = eLabel;
	}
}
