package mx.ipn.cic.controlescolar.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.ipn.cic.controlescolar.models.RolModel;

public interface RolRepository extends MongoRepository<RolModel, String> {

}
