package com.addressbookservice.service;

import com.addressbookservice.dto.AddressBookDTO;
import com.addressbookservice.exception.AddressBookException;
import com.addressbookservice.model.AddressBookData;
import com.addressbookservice.repository.AddressBookRepository;
import com.addressbookservice.util.EmailSenderService;
import com.addressbookservice.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    EmailSenderService sender;


    /**
     * accepts the contact data in the form of AddressBookDTO and stores it in DB
     * @param addressBookDTO - represents object of AddressBookDto class
     * @return accepted contact details information in JSON format
     * @token :-represent id
     */
    public AddressBookData createAddressBookData(AddressBookDTO addressBookDTO) {
        AddressBookData newAddress = new AddressBookData(addressBookDTO);
        addressBookRepository.save(newAddress);
        String token = tokenUtility.createToken(newAddress.getId());
        sender.sendEmail(newAddress.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +newAddress.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8080/addressBook/retrieve/"+token);
        return newAddress;
    }

    @Override
    public List<AddressBookData> getAddressBookDataByToken(String token) {
        return null;
    }

//    @Override
//    AddressBookData getRecordByToken(String token) {
//        return null;
//    }


//    /**
//     * accepts the contact data in the form of AddressBookDTO and
//     * updates the contact details having same Id from database
//     * @param token - represents contact details for same id
//     * @param addressBookDTO- represents object of AddressBookDTO class
//     * @return	updated Address Book information in JSON format
//     *Created service method which serves controller api to update record by token
//     */
//    public AddressBookData updateRecordByToken(String token, AddressBookDTO addressBookDTO) {
//        Integer id= tokenUtility.decodeToken(token);
//        Optional<AddressBookData> addressBook = addressBookRepository.findById(id);
//        if(addressBook.isEmpty()) {
//            throw new AddressBookException("Address Book Details for id not found");
//        }
//        AddressBookData newBook = new AddressBookData(id,addressBookDTO);
//        addressBookRepository.save(newBook);
//        sender.sendEmail(newBook.getEmail(), "Test Email", "Updated SuccessFully, hii: "
//                +newBook.getFirstName()+"Please Click here to get data of updated id-> "
//                +"http://localhost:8080/addressBook/retrieve/"+token);
//        return newBook;
//    }

    public AddressBookData updateRecordById(Integer id, AddressBookDTO addressBookDTO) {
        List<AddressBookData> addressList = addressBookRepository.findAll();
        AddressBookData newAddress = addressList.stream().filter(addressData -> addressData.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Specific address book details not found"));
        AddressBookData newAddressBook = new AddressBookData(id, addressBookDTO);
        addressBookRepository.save(newAddressBook);
        return newAddressBook;
    }

    /**accepts the contact Id and deletes the data of that contact from DB
     * @param Id - represents contact id
     * @return Id and Acknowledgment message
     */

    public void deleteRecordByToken(Integer Id) {

       AddressBookData deletecontact;
       addressBookRepository.deleteById(Id);

    }
    /**
     * getAll AddressBook list by token
     * @return list of contact information from DB by validating token first
     * @token :-represent id
     */


    public List<AddressBookData> getAddressBookDataById()
    {
        List<AddressBookData> isContactPresent=addressBookRepository.findAll();

            return isContactPresent;
        }



//    /**
//     *
//     * @param token is generated for all Address Book Data and it's unique for all contact.
//     * @return AddressBookData for particular token id
//     * @token :-represent id
//     */
//    public AddressBookData getRecordByToken(String token){
//        Integer id = tokenUtility.decodeToken(token);
//        Optional<AddressBookData> newAddressBook = addressBookRepository.findById(id);
//        if(newAddressBook.isEmpty()) {
//            log.warn("Unable to find address book details for given id: "+id);
//            throw new AddressBookException("Address Book Details not found for that particular id");
//        }
//        else {
//            sender.sendEmail("abhishekrajawat101@gmail.com", "Test Email", "Deleted SuccessFully, hii: "
//                    +newAddressBook.get().getEmail()+"Please Click here to get data-> "
//                    +"http://localhost:8080/addressBook/retrieve/"+token);
//
//            return newAddressBook.get();
//        }

    //Created method which serves controllers api call to delete record by id
    public void deleteById(Integer id){
        addressBookRepository.deleteById(id);
    }

    }










