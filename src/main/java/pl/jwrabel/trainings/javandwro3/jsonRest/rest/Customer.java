package pl.jwrabel.trainings.javandwro3.jsonRest.rest;

/**
 * Created by jakubwrabel on 23.03.2017.
 */
public class Customer {
	private String firstName;
	private String lastName;
	private String birthYear;
	private Double height;
	private String id;

	@Override
	public String toString() {
		return "Customer{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthYear='" + birthYear + '\'' +
				", height=" + height +
				", id='" + id + '\'' +
				'}';
	}

	public Customer() {
	}

	public Customer(String firstName, String lastName, String birthYear, Double height, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear;
		this.height = height;
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

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
