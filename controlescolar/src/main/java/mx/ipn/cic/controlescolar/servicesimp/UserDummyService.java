package mx.ipn.cic.controlescolar.servicesimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.ipn.cic.controlescolar.models.UserModel;
import mx.ipn.cic.controlescolar.repositories.UserDummyRepository;
import mx.ipn.cic.controlescolar.services.IUserService;

@Service
@Qualifier("DUMMY")
public class UserDummyService implements IUserService {

	@Autowired
	private UserDummyRepository userRepository;

	@Override
	public UserModel create(UserModel user) {
		UserModel u = userRepository.save(user);
		return u;
	}

	@Override
	public UserModel update(UserModel user) {
		UserModel u = userRepository.update(user);
		return u;
	}

	@Override
	public UserModel findById(int id) {
		UserModel found = userRepository.findById(id);
		// TODO Hacer manejo de Exceptions en este punto
		return found;
	}

	@Override
	public List<UserModel> findAll() {
		return userRepository.all();
	}

	@Override
	public boolean delete(UserModel user) {
		// TODO Manejar exeptions (No puedes eliminar algo que no existe)
		return userRepository.delete(user);
	}

}
