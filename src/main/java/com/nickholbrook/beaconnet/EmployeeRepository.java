package com.nickholbrook.beaconnet;

import com.nickholbrook.beaconnet.model.Employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}