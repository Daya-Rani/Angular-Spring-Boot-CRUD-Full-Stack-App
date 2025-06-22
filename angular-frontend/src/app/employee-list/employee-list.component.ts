import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  standalone: false,
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit{
  employees: Employee[]=[];
  constructor(private employeeService: EmployeeService,
              private router: Router
  ){}
  ngOnInit(): void {
    this.getEmployees();
  
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data =>{
      this.employees = data;
      console.log("this.emplyees:",this.employees)
    })

  }

  updateEmployee(id?: number){
      if (id !== undefined) {
    this.router.navigate(['/update-employee', id]);
  }
  }

  deleteEmployee(id?: number){
    if (id !== undefined) {
    this.employeeService.deleteEmployeeById(id).subscribe(() => {
      this.getEmployees();
    });
  }
  }
  viewEmployee(id?:number){
    if(id !== undefined){
      this.router.navigate(['/view-employee', id]);
    }
  }

}
