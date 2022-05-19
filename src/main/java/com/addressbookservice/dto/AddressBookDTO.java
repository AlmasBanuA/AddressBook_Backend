package com.addressbookservice.dto;


import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AddressBookDTO {

    @Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="Employee firstName is Not valid")
    public String firstName;

    @Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="Employee lastName is Not valid")
    public String lastName;

    @NotEmpty(message="Email cant be empty")
    public String email;

    @NotNull(message="Phone number cant be empty")
    public long phoneNumber;

    @NotEmpty(message="city cant be empty")
    public String city;

    @NotEmpty(message="State cant be empty")
    public String state;

    @NotNull(message="Zip code cant be empty")
    public int zip;

    public String address;

}
