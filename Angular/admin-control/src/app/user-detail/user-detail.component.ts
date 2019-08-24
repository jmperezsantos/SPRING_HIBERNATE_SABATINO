import { Component, OnInit, Input } from '@angular/core';
import { UserModel } from '../model/UserModel';
import { UserService } from '../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  @Input()
  user: UserModel;

  isEditing: boolean;

  userService: UserService;
  activatedRoute: ActivatedRoute
  router: Router;

  constructor(
    userService: UserService,
    activatedRoute: ActivatedRoute,
    router: Router
  ) {

    this.userService = userService;
    this.activatedRoute = activatedRoute;
    this.router = router;

  }

  ngOnInit() {

    let id =
      this.activatedRoute.snapshot.paramMap.get("id");

    this.userService.getUserById(
      id,
      user => {
        this.user = user;
      },
      error => {
        alert(error);
      }
    )

  }

  edit() {

    this.isEditing = true;

  }

  update() {

    //Invocación a WS.
    this.userService.updateUser(
      this.user,
      () => {
        
        this.router.navigateByUrl("/list");
        
      },
      error => {
        alert(error);
      }
    );

  }

}
