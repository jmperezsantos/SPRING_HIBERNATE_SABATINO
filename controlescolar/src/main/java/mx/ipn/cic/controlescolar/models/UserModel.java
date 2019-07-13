package mx.ipn.cic.controlescolar.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identifier")
	private Integer id;

	@NotNull
	@Size(min = 3, max = 25)
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 4, max = 10)
	@Column()
	private String lastname;

	@Column(name = "ap_materno")
	private String surname;

	@Min(value = 18)
	@Max(value = 70)
	@Column(name = "edad")
	private int age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public UserModel() {
		super();

		this.age = 18;
		this.name = "--";

	}

	public UserModel(int id, String name, String lastname, String surname, int age) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.surname = surname;
		this.age = age;
	}

	public UserModel(String name, String lastname, String surname, int age) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.surname = surname;
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("UserModel [id=%s, name=%s, lastname=%s, surname=%s, age=%s]", id, name, lastname, surname,
				age);
	}

}
