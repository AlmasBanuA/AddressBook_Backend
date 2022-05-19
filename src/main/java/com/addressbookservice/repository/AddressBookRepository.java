package com.addressbookservice.repository;

import com.addressbookservice.model.AddressBookData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBookData,Integer> {
}
/**
 * here AddressBookRepository we take all data of our entity which is EmployeePayrollData, and passes id as integer which is our
 * primary key of our model class i.e Employee Payroll data.
 */
