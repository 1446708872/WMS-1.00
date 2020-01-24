package indi.wzy.wms.model;

public class Inventory {
	private Integer ProductID;
	private String User;
	private String SynthesisTechnology;
	private String Capacity;
	private String Version;
	private Integer Amount;
	
	public Inventory() {
		
	}
	
	public String[] getAys() {
		String[] ays ={ProductID.toString(),User,SynthesisTechnology,Capacity,Version,Amount.toString()};
		return ays;
	}
	
	public Inventory(Integer productID, String user, String synthesisTechnology, String capacity, String version,
			Integer amount) {
		ProductID = productID;
		User = user;
		SynthesisTechnology = synthesisTechnology;
		Capacity = capacity;
		Version = version;
		Amount = amount;
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getSynthesisTechnology() {
		return SynthesisTechnology;
	}

	public void setSynthesisTechnology(String synthesisTechnology) {
		SynthesisTechnology = synthesisTechnology;
	}

	public String getCapacity() {
		return Capacity;
	}

	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}
}
