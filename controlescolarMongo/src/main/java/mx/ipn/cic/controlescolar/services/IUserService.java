package mx.ipn.cic.controlescolar.services;

import java.util.List;

import mx.ipn.cic.controlescolar.models.UserModel;

public interface IUserService {

	UserModel create(UserModel user);

	UserModel update(UserModel user);

	UserModel findById(String id);

	List<UserModel> findAll();

	boolean delete(UserModel user);

	UserModel findByName(String name);

	UserModel findByNameContaining(String name);
}
