package in.dreamnation.salesgun.models;

public class ServiceTable {

	String name, servicetype, address, email, price, description, mobile, thumbnailUrl;
	
	int serviceid;

    public ServiceTable() {
    }
 
    public ServiceTable(String name, String servicetype, String address, String email, String price, String description, String mobile, String thumbnailUrl) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.servicetype = servicetype;
        this.address = address;
        this.email = email;
        this.price = price;
        this.description = description;
        this.mobile = mobile;        
    }
    
    public ServiceTable(int id, String name, String servicetype, String address, String email, String price, String description, String mobile, String thumbnailUrl) {
        this.serviceid = id;
    	this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.servicetype = servicetype;
        this.address = address;
        this.email = email;
        this.price = price;
        this.description = description;
        this.mobile = mobile;        
    }
    
    public int getId() {
		return serviceid;
	}

	public void setId(int id) {
		this.serviceid = id;
	}
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
 
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
 
    public String getServiceType() {
        return servicetype;
    }
 
    public void setServiceType(String servicetype) {
        this.servicetype = servicetype;
    }
    
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPrice() {
        return price;
    }
 
    public void setPrice(String price) {
        this.price = price;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getMobile() {
        return mobile;
    }
 
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
