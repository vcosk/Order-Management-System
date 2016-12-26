package in.vikk.ordermgmt.items;

import in.vikk.ordermgmt.api.items.Item;

public class ItemImpl implements Item {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private double price;	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public boolean filterName(String pattern) {	
		return this.getName().contains(pattern) ;
	}
	
	@Override
	public boolean filterDescription(String pattern) {
		return this.getDescription().contains(pattern);
	}
	
	@Override
	public boolean filterPrice(String pattern) {
		return String.valueOf(this.getPrice()).contains(pattern);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemImpl other = (ItemImpl) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "ItemImpl [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}	
}
