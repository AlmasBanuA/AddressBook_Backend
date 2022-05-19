package com.addressbookservice.controller;


import com.addressbookservice.dto.AddressBookDTO;
import com.addressbookservice.dto.ResponseDTO;
import com.addressbookservice.exception.AddressBookException;
import com.addressbookservice.model.AddressBookData;
import com.addressbookservice.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addressBook")
@CrossOrigin
public class AddressBookController {


    @Autowired
    private IAddressBookService addressBookService;


    /**
     * create record
     * @apiNote accepts the  data in JSON format and stores it in DB
     * @param addressBookDTO - represents object of AddressBookDTO class
     * @return accepted address information in JSON format
     */
    @PostMapping(path = "/create")
    public ResponseEntity<String> addAddressBookData(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookData user = addressBookService.createAddressBookData(addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("New Contact Added in AddressBook ", user);
        return new ResponseEntity (respDTO, HttpStatus.CREATED);
    }


    /**
     *get all data by using token
     * @param :-generated for id
     * @return fields with Http status
     */
    @GetMapping(value = "/retrieve")
    public ResponseEntity<ResponseDTO> getAddressBookDataById()
    {
        List<AddressBookData> listOfContacts = addressBookService.getAddressBookDataById();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfContacts);
        return new ResponseEntity(dto,HttpStatus.OK);
    }



    /**
     * get data for particular id
     * Ability to get a record by token
     */
//    @GetMapping("/get/{token}")
//    public ResponseEntity<String> getRecordById(@PathVariable String token) throws AddressBookException {
//        AddressBookData newAddressBook = addressBookService.getRecordByToken(token);
//        ResponseDTO dto = new ResponseDTO("Address Book Record for particular id retrieved successfully",newAddressBook);
//        return new ResponseEntity(dto,HttpStatus.OK);
//    }


    /**
     * update  record data by token
     * @apiNote accepts the address book data in JSON format and updates the address having same Id from database
     * @param token - represents addressBook id
     * @param addressBookDTO - represents object of AddressBookDto class
     * @return	updated address information in JSON format
     */


    @PutMapping("/update/{token}")
    public ResponseEntity<String> updateRecordById(@PathVariable String token,@Valid @RequestBody AddressBookDTO addressBookDTO){
        AddressBookData entity = addressBookService.updateRecordByToken(token,addressBookDTO);
        ResponseDTO dto = new ResponseDTO("Address Book Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    /**
     * delete records from database using token
     * @apiNote accepts the Id and deletes the data of that specific from DB
     * @return Id and Acknowledgment message
     */


    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Integer Id){
        addressBookService.deleteRecordByToken(Id);
        return new ResponseEntity("deleted successfully",HttpStatus.OK);
    }

    //Delete  data by id in the repository
    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Integer id) {
        addressBookService.deleteById(id);
        return "Address of id: " + id + " has been deleted";
    }
}


