package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
//@EntityListeners({EmailAddressValidator.class})
public class Person implements EntityWithEmailAddress
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name_prefix")
	@Size(max = 255)
	private String namePrefix;

	@Column(name = "first_name")
	@Size(max = 255)
	private String firstName;

	@Column(name = "middle_name")
	@Size(max = 255)
	private String middleName;

	@Column(name = "last_name")
	@Size(max = 255)
	@NotNull
	private String lastName;

	@Column(name = "name_suffix")
	@Size(max = 255)
	private String nameSuffix;

	private Date birthdate;

	@Column(name = "email_address")
	@Size(max = 255)
	private String emailAddress;

	@ManyToOne
	@JoinColumn(name = "division_id")
	private Division division;

	@Column(name = "customer_code")
	@Size(max = 255)
	private String customerCode;

	// this is really JSON data
	// figure out how to make this JSON by looking at:
	//    https://devlearnings.wordpress.com/2014/03/28/using-postgres-json/
	private String metadata;

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}

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

	public Date getBirthdate()
	{
		return birthdate;
	}
	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	public Division getDivision()
	{
		return division;
	}
	public void setDivision(Division division)
	{
		this.division = division;
	}

	public String getCustomerCode()
	{
		return customerCode;
	}
	public void setCustomerCode(String customerCode)
	{
		this.customerCode = customerCode;
	}

	public String getNamePrefix()
	{
		return namePrefix;
	}
	public void setNamePrefix(String namePrefix)
	{
		this.namePrefix = namePrefix;
	}

	public String getMiddleName()
	{
		return middleName;
	}
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getNameSuffix()
	{
		return nameSuffix;
	}
	public void setNameSuffix(String nameSuffix)
	{
		this.nameSuffix = nameSuffix;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public String getMetadata()
	{
		return metadata;
	}
	public void setMetadata(String metadata)
	{
		this.metadata = metadata;
	}
}
