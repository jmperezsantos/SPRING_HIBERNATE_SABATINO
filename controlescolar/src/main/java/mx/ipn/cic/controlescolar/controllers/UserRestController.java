package mx.ipn.cic.controlescolar.controllers;

import mx.ipn.cic.controlescolar.exceptions.CICException;
import mx.ipn.cic.controlescolar.models.UserModel;
import mx.ipn.cic.controlescolar.services.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/user")
public class UserRestController {

    private static final Log LOOGGER =
            LogFactory.getLog(UserRestController.class);

    @Autowired
    @Qualifier("REAL")
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAll() {

        List<UserModel> all = userService.findAll();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    //public UserModel getById(
    //      @PathVariable(name = "id") int id
    //) {
    public ResponseEntity<UserModel> getById(
            @PathVariable(name = "id") int id
    ) {

        ResponseEntity<UserModel> response;

        try {

            UserModel user = userService.findById(id);

            response = new ResponseEntity<UserModel>(user, HttpStatus.OK);

            return response;

        } catch (CICException e) {

            LOOGGER.error(e.getMessage());

            //Los heades se utilizan para enviar información adicional al cliente.
            MultiValueMap headers = new LinkedMultiValueMap();
            headers.set("CUSTOM_RESPONSE_VALUE", e.getMessage());

            response =
                    new ResponseEntity<UserModel>(headers, HttpStatus.NOT_FOUND);

        }

        return response;
    }

    @PostMapping()
    public ResponseEntity<UserModel> createNew(@RequestBody UserModel user) {

        user = userService.create(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<UserModel> update(@RequestBody UserModel user) {

        ResponseEntity<UserModel> response;
        try { // Happy Path

            user = userService.update(user);

            response = new ResponseEntity<UserModel>(user, HttpStatus.ACCEPTED);

            return response;

        } catch (CICException e){

            LOOGGER.error(e.getMessage());

            //Los heades se utilizan para enviar información adicional al cliente.
            MultiValueMap headers = new LinkedMultiValueMap();
            headers.set("ERROR_MESSAGE", e.getMessage());

            response = new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);

        }

        return response;

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(name = "id") int id) {

        ResponseEntity<Boolean> response;

        try {

            UserModel user = userService.findById(id);

            userService.delete(user);

            response = new ResponseEntity<>(true, HttpStatus.ACCEPTED);

            return response;

        } catch (CICException e) {

            LOOGGER.error(e.getMessage());

            //Los heades se utilizan para enviar información adicional al cliente.
            MultiValueMap headers = new LinkedMultiValueMap();
            headers.set("ERROR_MESSAGE", e.getMessage());

            response = new ResponseEntity<>(false, headers, HttpStatus.NOT_ACCEPTABLE);

        }

        return response;

    }

}