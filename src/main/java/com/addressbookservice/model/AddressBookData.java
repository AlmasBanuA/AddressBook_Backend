package com.addressbookservice.model;


import com.addressbookservice.dto.AddressBookDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Address_Book_Service_Application")
public @Data class AddressBookData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private String city;
    private String state;
    private int zip;
    private String address;

    public AddressBookData(AddressBookDTO addressBookDTO) {
        this.updateAddressBook(addressBookDTO);
    }

    public AddressBookData() {
    }

    public AddressBookData(Integer id, AddressBookDTO addressBookDTO) {
        this.id=id;
        this.firstName = addressBookDTO.getFirstName();
        this.lastName = addressBookDTO.getLastName();
        this.email = addressBookDTO.getEmail();
        this.phoneNumber = addressBookDTO.getPhoneNumber();
        this.city = addressBookDTO.getCity();
        this.state = addressBookDTO.getCity();
        this.zip = addressBookDTO.getZip();
        this.address= addressBookDTO.getAddress();

    }

    public void updateAddressBook(AddressBookDTO addressBookDTO){
        this.firstName=addressBookDTO.firstName;
        this.lastName=addressBookDTO.lastName;
        this.email=addressBookDTO.email;
        this.phoneNumber=addressBookDTO.phoneNumber;
        this.city=addressBookDTO.city;
        this.state=addressBookDTO.state;
        this.zip=addressBookDTO.zip;
        this.address=addressBookDTO.address;
    }
}
