package mx.ipn.cic.controlescolar.servicesimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ipn.cic.controlescolar.exceptions.CICException;
import mx.ipn.cic.controlescolar.models.RolModel;
import mx.ipn.cic.controlescolar.repositories.RolRepository;
import mx.ipn.cic.controlescolar.services.IRolService;

@Service
public class RolService implements IRolService {

	@Autowired
	private RolRepository repository;

	@Override
	public List<RolModel> getAll() {

		return repository.findAll();

	}

	@Override
	public RolModel getById(Integer id) throws CICException {

		Optional<RolModel> findById = repository.findById(id);

		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new CICException("No se encontró el rol");
		}

	}
	
	@Override
	public RolModel save(RolModel rol) {
		
		return repository.save(rol);
		
	}
	
	@Override
	public RolModel update(RolModel rol) {
		
		return repository.save(rol);
	}
	
	@Override
	public boolean delete (RolModel rol) {
		
		repository.delete(rol);
		
		return true;
		
	}

}
