import {Component, OnInit, TemplateRef} from '@angular/core';
import {UserService} from "../../services/user.service";
import {EmployeeFilter} from "../../model/employee-filter";
import {BsModalRef} from "ngx-bootstrap/modal/bs-modal-ref.service";
import {BsModalService} from "ngx-bootstrap/modal";

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.css']
})
export class EmployeesListComponent implements OnInit {
  employeeFilters: EmployeeFilter;
  public modalRef: BsModalRef;
  allEmployees;
  employeeEdit = {};
  constructor(private userService: UserService, private modalService: BsModalService) {
    this.employeeFilters = new EmployeeFilter();
    this.employeeFilters.id = "";
    this.employeeFilters.name = "";
    this.employeeFilters.surname = "";
  }

  ngOnInit() {
    this.userService.getFilteredUsers(this.employeeFilters).subscribe(data => {
      this.allEmployees = data;
      console.log(this.allEmployees)
    })
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(
      template,
      Object.assign({}, { class: 'modal-sm' })
    );
  }

  filtrArticlesList(){
    this.userService.getFilteredUsers(this.employeeFilters).subscribe(data => {
      this.allEmployees = data;
      console.log(this.allEmployees)
    });
  }

  edit(login){
    this.userService.getUserInfo(login).subscribe(data => {
      this.employeeEdit = data;
    });
    console.log(login);
  }

  updateEmployee(){
    this.userService.updateEmployee(this.employeeEdit).subscribe(data => {
      this.userService.getFilteredUsers(this.employeeFilters).subscribe(data => {
        this.allEmployees = data;
        console.log(this.allEmployees)
      });
    })
  }
}
