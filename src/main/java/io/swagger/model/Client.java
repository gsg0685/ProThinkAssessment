package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name="Client_tbl")
public class Client   {
  @JsonProperty("id")
  @Id
  @Min(1)
  @Max(13)
  @Pattern(regexp = "^(\\\\d{2})(\\\\d{2})(\\\\d{2})(\\\\d)(\\\\d{3})([01])(\\\\d)(\\\\d)$\"")
  private String id = null;
  
  
  @JsonProperty("physicalAddress")
  private String physicalAddress=null;

  public String getPhysicalAddress() {
	return physicalAddress;
}

public void setPhysicalAddress(String physicalAddress) {
	this.physicalAddress = physicalAddress;
}

@JsonProperty("firstName")
  private String firstName = null;

  public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

/*
 * public Address getAddress() { return address; }
 * 
 * public void setAddress(Address address) { this.address = address; }
 */
@JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;



  public Client id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the organization
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier of the organization")
  @NotNull


  public String getId() {
    return id;
  }

  @Override
public String toString() {
	return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", physicalAddress="+physicalAddress+",phoneNumber=" + phoneNumber
			+ "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
	result = prime * result + ((physicalAddress == null) ? 0 : physicalAddress.hashCode());
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
	Client other = (Client) obj;
	
	if (firstName == null) {
		if (other.firstName != null)
			return false;
	} else if (!firstName.equals(other.firstName))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (lastName == null) {
		if (other.lastName != null)
			return false;
	} else if (!lastName.equals(other.lastName))
		return false;
	if (phoneNumber == null) {
		if (other.phoneNumber != null)
			return false;
	} else if (!phoneNumber.equals(other.phoneNumber))
		return false;
	
	if (physicalAddress == null) {
		if (other.physicalAddress != null)
			return false;
	} else if (!physicalAddress.equals(other.physicalAddress))
		return false;
	
	return true;
}

public void setId(String id) {
    this.id = id;
  }

  

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

