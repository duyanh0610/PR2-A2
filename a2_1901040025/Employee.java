package a2_1901040025;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview represent the employees who work in organization
 * @attributes id 			Integer 	int 
 * 			   firstName 	String 
 * 			   givenName 	String 
 * 			   lastName 	String
 *             email 		String 
 *             address	    String 
 *             phone 		String 
 *             dob 			Date 
 *             gender 		Gender
 * @object a typical employees is c = <i, fn, gn, ln, e, a, p, d, g >, where
 *         id(i), firstName(fn), givenName(gn),lastName(ln), email(e),
 *         address(a), phone(p), dob(d), gender(g)
 * @abstract_properties	
 * <pre>
 * 	(// domain contraints) 
 * 		mutable(id) = F /\ optional(id)= F /\ min(id)=100 /\ 
 * 		mutable(firstName) = T /\  optional(firstName)= F /\ length(firstName)=35 /\
 *      mutable(givenName) = T /\ optional(givenName)= F /\ length(givenName)=35 /\ 
 *      mutable(lastName) = T /\  optional(lastName)= F /\ length(lastName)=35 /\
 *      mutable(email) = T /\ optional(email)=T /\ length (email)= 200 /\ email must have format X@Y where X, Y are words containing at least one alphanumeric letter and Y must contain at least one ‘.’/\
 *      mutable (address) =T /\ optional (address) = F /\length(address) = 1500 /\
 *      mutable  (phone) = T /\ optional (phone)= F /\ length (phone) =20 /\ phone  is (84) N, where N is a number containing at least 8 and at most 15 digits /\
 *      mutable (dob) =F /\ optional (dob) = F /\ min(dob)=1/1/1990 /\ 
 *      mutable (Gender) = F /\ optional (gender) = F
 * </pre>
 */
public class Employee {
	// attributes
	@DomainConstraint(type = "Integer", mutable = false, optional = false, min = 100)
	private int id;
	
	@DomainConstraint(type = "String", mutable = true, optional = false, length = 35)
	private String firstName;
	
	@DomainConstraint(type = "String", mutable = true, optional = false, length = 35)
	private String givenName;
	
	@DomainConstraint(type = "String", mutable = true, optional = false, length = 35)
	private String lastName;
	
	@DomainConstraint(type = "String", mutable = true, optional = true, length = 200)
	private String email;
	
	@DomainConstraint(type = "String", mutable = true, optional = false, length = 1500)
	private String address;
	
	@DomainConstraint(type = "String", mutable = true, optional = false, length = 20)
	private String phone;
	
	@DomainConstraint(type = "Date", mutable = false, optional = false, min = 1 / 1 / 1990)
	private Date dob;
	
	@DomainConstraint(type = "Gender", mutable = false, optional = false)
	private Gender gender;

	/**
	 * @effect
	 * 
	 *         <pre>
	 * if id, firstname, givenname, lastname, email, address, phone, dob, gender are valid 
	 * 		intit this as  <id, firstName, givenName, lastName, email, address, phone, date, gender> 
	 * else 
	 * 		throw NotPossibleException
	 *         </pre>
	 */
	public Employee(@AttrRef("id") int id,@AttrRef("firstName") String firstName,@AttrRef("givenName") String givenName,@AttrRef("lastName") String lastName,
					 @AttrRef("email") String email,@AttrRef("address") String address, @AttrRef("phone") String phone,
					 @AttrRef("dob") Date dob,@AttrRef("gender") Gender gender) throws NotPossibleException  {
		if (!validateId(id)) {
			// id is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid id: " + id);
		}
		if (!validateFirstName(firstName)) {
			// first name is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid first name: " + firstName);
		}
		if (!validateGivenName(givenName)) {
			// given name is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid given name: " + givenName);
		}
		if (!validateLastName(lastName)) {
			// last name is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid last name: " + lastName);
		}
		if (!validateEmail(email)) {
			// email is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid email: " + email);
		}
		if (!validateAddress(address)) {
			// address is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid address: " + address);
		}
		if (!validatePhone(phone)) {
			// phone is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid phone: " + phone);
		}
		if (!validateGender(gender)) {
			// gender is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid gender: " + gender);
		}

		if (!validateDob(dob)) {
			// dob is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid dob: " + dob);
		}
		// id, firstname, givename, lastname, address, email, phone, gender, dob, are both valid: initialise this as <id,firstname,givename,lastname,address,email,phone,gender,dob>
		this.id = id;
		this.firstName = firstName;
		this.givenName = givenName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
	}
	/**
	 * @effect
	 * 
	 *         <pre>
	 * if id, firstname, givenname, lastname, address, phone, date, gender are valid 
	 * 		intit this as  <id, firstName, givenName, lastName, email, address, phone, date, gender> 
	 * else 
	 * 		throw NotPossibleException
	 *         </pre>
	 */
	public Employee(@AttrRef("id") int id,@AttrRef ("firstName") String firstName,@AttrRef("givenName") String givenName,@AttrRef ("lastName") String lastName,
					 @AttrRef("address") String address, @AttrRef("phone") String phone, @AttrRef("dob") Date dob,@AttrRef("gender") Gender gender) throws NotPossibleException  {
		if (!validateId(id)) {
			// id is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid id: " + id);
		}
		if (!validateFirstName(firstName)) {
			// first name is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid first name: " + firstName);
		}
		if (!validateGivenName(givenName)) {
			// given name is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid given name: " + givenName);
		}
		if (!validateLastName(lastName)) {
			// last name is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid last name: " + lastName);
		}
		if (!validateAddress(address)) {
			// address is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid address: " + address);
		}
		if (!validatePhone(phone)) {
			// phone is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid phone: " + phone);
		}
		if (!validateGender(gender)) {
			// gender is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid gender: " + gender);
		}

		if (!validateDob(dob)) {
			// dob is invalid, initialise this as <> and print error
			throw new NotPossibleException("Invalid dob: " + dob);
		}
		// < id, firstname, givename, lastname, address, phone, gender, dob, are both valid: initialise this as <id,firstname,givename,lastname,address,phone,gender,dob> 

		this.id = id;
		this.firstName = firstName;
		this.givenName = givenName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.dob = dob;

	}

	final static int Length_phone = 20;
	final static int Length_firstName= 35;
	final static int Length_givenName= 35;
	final static int Length_lastName= 35;
	final static int Length_email= 200;
	final static int Length_address= 1500;
	/**
	 * @effects return <tt>id</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("id")
	public int getId() {
		return id;
	}
	/**
	 * @effect return <tt> firstName</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @effect return <tt> givenName</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("givenName")
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @effect return <tt> lastName</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @effect return <tt> email</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("email")
	public String getEmail() {
		return email;
	}

	/**
	 * @effects return <tt> address</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("address")
	public String getAddress() {
		return address;
	}

	/**
	 * @effects return <tt> phone</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * @effects return <tt> date</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("dob")
	public Date getDob() {
		return dob;
	}

	/**
	 * @effects return <tt> last name + given name+ first name</tt>
	 */
	public String getName() {
		return lastName + " " + givenName + " " + firstName;
	}

	/**
	 * @effect return <tt> gender</tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("gender")
	public Gender getGender() {
		return gender;
	}

	/**
	 * @effects return <tt> year of birth </tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("dob")
	public int getYob() {
		Calendar cld = Calendar.getInstance();
	    cld.setTime(dob); 
	    int getyear = cld.get(Calendar.YEAR);
	    return getyear;   // return year of birth 
	}

	/**
	 * @modifies this.firstName
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if firstName is valid
	 *      set this.firstName = newfirstName
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */

	@DOpt(type = OptType.Mutator)
	@AttrRef("firstName")
	public boolean setFirstName(String firstName) {
		if (validateFirstName(firstName)) {
			this.firstName = firstName;
			return true;
		}
		return false;
	}

	/**
	 * @modifies this.givenName
	 * @effects
	 * 
	 *          <pre>
	 *  if givenName is valid
	 *      set this.givenName = newgivenName
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */

	@DOpt(type = OptType.Mutator)
	@AttrRef("givenName")
	public boolean setGivenName(String givenName) {
		if (validateGivenName(givenName)) {
			this.givenName = givenName;
			return true;
		}
		return false;

	}

	/**
	 * @modifies this.lastName
	 * @effects
	 * 
	 *          <pre>
	 * if lastName is valid 
	 * set this.lastName = newlastName 
	 * return true 
	 * else 
	 * return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("lastName")
	public boolean setLastName(String lastName) {
		if (validateLastName(lastName)) {
			this.lastName = lastName;
			return true;
		}
		return false;

	}

	/**
	 * @modifies this.email
	 * @effects
	 * 
	 *          <pre>
	 * if email is valid 
	 * set this.email = newemail 
	 * return true 
	 * else
	 * return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("email")
	public boolean setEmail(String email) {
		if (validateEmail(email)) {
			this.email = email;
			return true;
		}
		return false;
	}

	/**
	 * @modifies this.address
	 * @effects
	 * 
	 *          <pre>
	 * if address is valid 
	 * set this.address = address 
	 * return true 
	 * else 
	 * return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("address")
	public boolean setAddress(String address) {
		if (validateAddress(address)) {
			this.address = address;
			return true;
		}
		return false;
	}

	/**
	 * @modifies this.phone
	 * @effects
	 * 
	 *          <pre>
	   * if phone is valid 
	  * set this.phone = newphone 
	    * return true 
	    * else
	    * return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("phone")
	public boolean setPhone(String phone) {
		if (validatePhone(phone)) {
			this.phone = phone;
			return true;
		}
		return false;
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if id is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateId(int id) {
		//optional
		//length
		// min
		if (id < 100) {
			return false;
		}
		return true;
		// max
		//other
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if first is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateFirstName(String firstName) {
		// optional
		if (firstName == null || "".equals(firstName)) {
			return false;
		} 
		//length
		if (firstName.length() > Length_firstName) {
			return false;
			
		}
		return true;
			
			// min
			// max
			// other
		
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if givenName is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateGivenName(String givenName) {
		//optional
		if (givenName == null || "".equals(givenName)) {
			return false;
		}
		//length
		if (givenName.length() > Length_givenName) {
			return false;
		}
		return true;
	
		//min
		//max
		//other
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if lastName is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateLastName(String lastName) {
		//optional
		if (lastName == null || " ".equals(lastName)) {
			return false;
		}
		//length
		if (lastName.length()> Length_lastName) {
			return false;
		}
		return true;		
		//min
		//max
		//other
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if email is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateEmail(String email) {
	//optional 
	//length
		if (email.length() > Length_email) {
			return false;
		}
	//min 
	//max
	//other
		String emailform = "^(.+)@(.+)\\.{1,}(.+)$"; 
		return email.matches(emailform);
		
	}
	
	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if address is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateAddress(String address) {
		//optional
		if (address == null || " ".equals(address)) {
			return false;
		}
		//length
		if(address.length()> Length_address) {
			return false;
		}
		return true;
		//min
		//max
		//other
}


	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if phone is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validatePhone(String phone) {
		//optional
		//length
		if (phone.length() > Length_phone) {
			return false;
		}
		//min 
		//max
		//other
		String phoneform = "^(\\(84\\)\\s){1}\\d{8,15}$";
		return phone.matches(phoneform);
		
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if dob is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateDob(Date dob) {
		//optional
		if(dob == null) {
			return false;
		}
		//length
		//min
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Date min = null;
		try {
			min = date.parse("1/1/1900");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if ( dob.before(min)) {	
			return false;
		} else {
		return true;
		}
		//max
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *  
	 *  if gender is valid
	 *      return true
	 *  else
	 *      return false
	 *          </pre>
	 */
	public boolean validateGender(Gender gender) {
		//optional
		//length
		//min
		//max
		//other
		if (gender == null) {
			return false;
		}
		return true;
	}

	@DOpt(type = OptType.Helper)
	public boolean repOk() {
		return validateId(id) && validateFirstName(firstName) && validateGivenName(givenName)
				&& validateLastName(lastName) && validateAddress(address) && validateEmail(email)
				&& validatePhone(phone) && validateGender(gender) && validateDob(dob);
	}
	@Override
	public String toString() {
		return "Employees [id:" + id + ", First Name:" + firstName + ", Given Name:" + givenName + ", Last Name:"
				+ lastName + ", Email:" + email + ", Address:" + address + ", Phone:" + phone + ", Date of birth:" + dob
				+ ", Gender:" + gender + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (givenName == null) {
			if (other.givenName != null)
				return false;
		} else if (!givenName.equals(other.givenName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	
}
