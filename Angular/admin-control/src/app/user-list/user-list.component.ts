import { Component, OnInit } from '@angular/core';
import { UserModel } from '../model/UserModel';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  //Instancia "local" del servicio
  private userService: UserService;
  users: UserModel[];

  isLoading: boolean;

  constructor(
    userService: UserService
  ) {

    //Guardamos la instancia del servicio
    this.userService = userService;

  }

  ngOnInit() {

    this.isLoading = true;
    this.userService.getUsers(
      users => {

        this.users = users;

        this.isLoading = false;

      },
      error => {
        alert(error);

        this.isLoading = false;
      }
    )
  }

  delete(user: UserModel) {

    this.userService.deleteUserById(
      "" + user.id,
      () => {
        this.ngOnInit();
      },
      error => {
        alert(error);
      }
    )

  }

}
