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

  getUsers(): UserModel[] {

    //TODO Esto debe venir del WS
    var users: UserModel[] = [
      { id: 1, name: "Manuel", lastname: "Pérez", surname: "Santos", age: 30 },
      { id: 2, name: "Juan", lastname: "Santos", surname: "Ruiz", age: 25 },
      { id: 3, name: "Catalina", lastname: "Del Cueto", surname: "Pérez", age: 19 },
    ]

    return users;

  }

  registerUser(user: UserModel) {

    //Registrar un usuario por WS
    var myHeaders = {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin":"*",
      "Access-Control-Allow-Headers":"Origin, X-Requested-With, Content-Type, Accept"
    }

    this.httpClient.post(
      "http://localhost:8080/rest/user", //URL
      user,//Body
      {
        headers: myHeaders //Headers
      }).subscribe(
        data=>{//Caso de éxito!
          
          var registered = data as UserModel;
          alert("Usuario registrado " + registered.id);

        },
        error=>{//Caso de error!
          
          alert("Error al registrar usuario");
        }
      );

  }

}
