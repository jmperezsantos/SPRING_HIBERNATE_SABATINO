package mx.ipn.cic.controlescolar.services;

import java.util.List;

import mx.ipn.cic.controlescolar.models.RolModel;

public interface IRolService {

	List<RolModel> getAll();

	RolModel getById(String id);

	RolModel save(RolModel rol);

	RolModel update(RolModel rol);

	boolean delete(RolModel rol);

}