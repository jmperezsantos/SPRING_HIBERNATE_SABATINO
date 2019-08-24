import { Injectable } from '@angular/core';
import { UserModel } from '../model/UserModel';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserService {

  private httpClient: HttpClient;

  constructor(
    httpClient: HttpClient
  ) {

    this.httpClient = httpClient;

  }

  getUsers(
    success: (users: UserModel[]) => void,
    error: (message: string) => void
  ) {

    var myHeaders = {
      "Content-Type": "application/json"
    }

    this.httpClient.get(
      "http://localhost:8080/rest/user",
      { headers: myHeaders }
    ).subscribe(
      (data) => { //Caso éxito

        //Se crea el arreglo de usuarios
        var users: UserModel[] = []

        //Parsing *
        users = data as UserModel[];

        success(users);

      },
      err => {

        error("Error al consultar los usuarios");

      },
      () => {

        console.log("Se terminó el consumo del WS");

      }
    );

  }

  registerUser(user: UserModel) {

    //Registrar un usuario por WS
    var myHeaders = {
      "Content-Type": "application/json"
    }

    this.httpClient.post(
      "http://localhost:8080/rest/user", //URL
      user,//Body
      {
        headers: myHeaders //Headers
      }).subscribe(
        data => {//Caso de éxito!

          var registered = data as UserModel;
          alert("Usuario registrado " + registered.id);

        },
        error => {//Caso de error!

          alert("Error al registrar usuario");
        }
      );

  }

  getUserById(
    id: string,
    success: (user: UserModel) => void,
    error: (error: string) => void
  ) {

    let myHeaders = {
      "Content-Type": "application/json"
    };

    let url = "http://localhost:8080/rest/user/" + id;

    this.httpClient.get(
      url,    //Url a consultar
      {
        headers: myHeaders    //Headers
      }).subscribe(
        data => {   //Caso éxito

          //"as" se ocupa para hacer "casting"
          let user = data as UserModel;
          success(user);

        },
        err => {      //Caso error
          error("Usuario no encontrado");
        }
      );

  }

  deleteUserById(
    id: string,
    success: () => void,
    error: (error: string) => void
  ) {

    let myHeaders = {
      "Content-Type": "application/json"
    };

    let url = "http://localhost:8080/rest/user/" + id;

    this.httpClient.delete(
      url,    //Url a consultar
      {
        headers: myHeaders    //Headers
      }).subscribe(
        data => {   //Caso éxito

          //"as" se ocupa para hacer "casting"
          let result = data as boolean;

          if (result) {
            success();
          }

        },
        err => {      //Caso error
          error("Usuario no encontrado");
        }
      );
  }

  updateUser(
    user: UserModel,
    success: () => void,
    error: (message: string) => void
  ) {

    //Actualizar un usuario por WS
    var myHeaders = {
      "Content-Type": "application/json"
    }

    this.httpClient.put(
      "http://localhost:8080/rest/user", //URL
      user,//Body
      {
        headers: myHeaders //Headers
      }).subscribe(
        data => {//Caso de éxito!

          var registered = data as UserModel;
          success();

        },
        err => {//Caso de error!

          error("No se pudo actualizar el usuario");

        }
      );

  }

}
