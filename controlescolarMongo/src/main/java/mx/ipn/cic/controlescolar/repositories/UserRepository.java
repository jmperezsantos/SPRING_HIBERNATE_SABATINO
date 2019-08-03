package mx.ipn.cic.controlescolar.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ipn.cic.controlescolar.models.RolModel;
import mx.ipn.cic.controlescolar.models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{

	//Busca por coincidencia exacta
	UserModel findByName(String name);
	
	//Busca por coincidencia parcial (contains"
	UserModel findByNameContains(String name);
	
	//Busca por coincidencia parcial (contains"
	UserModel findByNameLike(String name);
	
	List<UserModel> findByRol(RolModel rol);
	
	List<UserModel> findByRol_Name(String rolName);
	
}








