package mx.ipn.cic.controlescolar.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.ipn.cic.controlescolar.models.UserModel;

@Repository
public class UserDummyRepository {

	private int count = 0;
	private List<UserModel> list = new ArrayList<>();
	
	public UserDummyRepository() {
		list =  new ArrayList<UserModel>();
		list.add(new UserModel(1,"Fermin","Flores","Jasso",26));
		list.add(new UserModel(2,"Manuel","Perez","Santos",30));
		list.add(new UserModel(3,"Tommy","Flores","Jasso",9));
		
		count = 3;

	}
	

	public UserModel save(UserModel user) {
		user.setId(++count);
		this.list.add(user);
		return user;
	}

	public UserModel update(UserModel user) {
		
		UserModel originalUser = this.findById(user.getId());
		
		originalUser.setAge(user.getAge());
		originalUser.setLastname(user.getLastname());
		originalUser.setName(user.getName());
		originalUser.setSurname(user.getSurname());

		return originalUser;
		
	}

	public UserModel findById(int id) {
		for (UserModel user : this.list) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public List<UserModel> all() {
		return this.list;
	}

	public boolean delete(UserModel user) {
		if (list.contains(user)) {
			return list.remove(user);
		}
		return false;
	}
}
