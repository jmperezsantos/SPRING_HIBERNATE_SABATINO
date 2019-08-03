package mx.ipn.cic.controlescolar.models;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotNull
	@Size(min = 3, max = 25)
	private String name;

	@NotNull
	@Size(min = 4, max = 10)
	private String lastname;

	private String surname;

	@Min(value = 18)
	@Max(value = 70)
	private int age;

	private RolModel rol;

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

	public RolModel getRol() {
		return rol;
	}

	public void setRol(RolModel rol) {
		this.rol = rol;
	}

	public UserModel() {
		super();

		this.age = 18;
		this.name = "--";

	}

	public UserModel(String id, String name, String lastname, String surname, int age) {
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
		return String.format("UserModel [id=%s, name=%s, lastname=%s, surname=%s, age=%s, rol=%s]", id, name, lastname,
				surname, age, rol);
	}

}
