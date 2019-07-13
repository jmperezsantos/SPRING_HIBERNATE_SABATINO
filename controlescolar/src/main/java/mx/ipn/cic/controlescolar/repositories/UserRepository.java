package mx.ipn.cic.controlescolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.ipn.cic.controlescolar.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	UserModel findByName(String name);
	
}








