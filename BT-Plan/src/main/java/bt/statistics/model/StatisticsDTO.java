package bt.statistics.model;

public class StatisticsDTO {

	private int mindex;
	private int mtotalconnection;
	private int mplannerconnection;
	private int mwebfolderconnection;
	private int mtextconnection;
	private int mimageconnection;
	public StatisticsDTO() {
		super();
	}
	public StatisticsDTO(int mindex, int mtotalconnection, int mplannerconnection, int mwebfolderconnection,
			int mtextconnection, int mimageconnection) {
		super();
		this.mindex = mindex;
		this.mtotalconnection = mtotalconnection;
		this.mplannerconnection = mplannerconnection;
		this.mwebfolderconnection = mwebfolderconnection;
		this.mtextconnection = mtextconnection;
		this.mimageconnection = mimageconnection;
	}
	public int getMindex() {
		return mindex;
	}
	public void setMindex(int mindex) {
		this.mindex = mindex;
	}
	public int getMtotalconnection() {
		return mtotalconnection;
	}
	public void setMtotalconnection(int mtotalconnection) {
		this.mtotalconnection = mtotalconnection;
	}
	public int getMplannerconnection() {
		return mplannerconnection;
	}
	public void setMplannerconnection(int mplannerconnection) {
		this.mplannerconnection = mplannerconnection;
	}
	public int getMwebfolderconnection() {
		return mwebfolderconnection;
	}
	public void setMwebfolderconnection(int mwebfolderconnection) {
		this.mwebfolderconnection = mwebfolderconnection;
	}
	public int getMtextconnection() {
		return mtextconnection;
	}
	public void setMtextconnection(int mtextconnection) {
		this.mtextconnection = mtextconnection;
	}
	public int getMimageconnection() {
		return mimageconnection;
	}
	public void setMimageconnection(int mimageconnection) {
		this.mimageconnection = mimageconnection;
	}
	
	
}
