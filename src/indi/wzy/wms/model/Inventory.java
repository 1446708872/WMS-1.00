package indi.wzy.wms.model;

public class Inventory {
	private Integer ProductID;
	private String User;
	private String SynthesisTechnology;
	private String Capacity;
	private String Version;
	private Integer Amount;
	
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
