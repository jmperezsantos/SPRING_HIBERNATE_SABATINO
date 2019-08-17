import { Component, OnInit } from '@angular/core';
import { UserModel } from '../model/UserModel';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-new-user-form',
  templateUrl: './new-user-form.component.html',
  styleUrls: ['./new-user-form.component.css']
})
export class NewUserFormComponent implements OnInit {

  user: UserModel
  userService: UserService

  constructor(
    userService: UserService
  ) {

    this.userService = userService;

    this.user = new UserModel();
    this.user.name = "manuel";

  }

  ngOnInit() {

  }

  performClickAction() {

    //Registrar el usuario por medio de un WS
    //alert("Registrando usuario");

    this.userService.registerUser(this.user);

  }

}
