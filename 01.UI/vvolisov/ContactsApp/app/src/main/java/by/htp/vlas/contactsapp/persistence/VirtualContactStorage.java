package by.htp.vlas.contactsapp.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.vlas.contactsapp.Contact;

/**
 * Created by VlasEL on 17.02.2015 19:03
 */
public class VirtualContactStorage implements ContactStorage {

    private List<Contact> contactsList = new ArrayList<>();

    //generate random contacts data
    {
        for (int i = 0; i < 100; i++) {
            saveOrUpdate(generateContact(i));
        }
    }

    private VirtualContactStorage() {
    }

    public Contact generateContact(int pPositionNumber){
        Contact contact = new Contact(
                String.valueOf((int) ((Math.random() + 1) * 1111111))
                , "Name " + pPositionNumber
                , "mail" + pPositionNumber + "@server"
                , "Street " + pPositionNumber
                , new Date(1995 + pPositionNumber, 10, 15)
                , "user " + pPositionNumber
        );
        return contact;
    }

    @Override
    public List<Contact> list() {
        return contactsList;
    }

    @Override
    public Contact get(int pId) {
        if (pId < 0 || pId >= contactsList.size()) {
            return null;
        }
        return contactsList.get(pId);
    }

    @Override
    public Contact saveOrUpdate(Contact pContact) {
        Contact contact = pContact;
        if(pContact == null) {
            throw new NullPointerException();
        }
        if(pContact.getId() == null) {
            contact = insert(pContact);
        } else {
            contact = get(pContact.getId());
            if(contact != null) {
                updateContactData(pContact, contact);
            } else {
                throw new RuntimeException("Entity with id = " + pContact.getId()
                        + " not found in persistence storage!");
            }
        }
        return contact;
    }

    private Contact insert(Contact pContact) {
        Contact contact = new Contact(pContact);
        contact.setId(contactsList.size());
        contactsList.add(contact);
        return contact;
    }

    private void updateContactData(Contact pContactFrom, Contact pContactTo) {
        pContactTo.setPhone(pContactFrom.getPhone());
        pContactTo.setName(pContactFrom.getName());
        pContactTo.setEmail(pContactFrom.getEmail());
        pContactTo.setAddress(pContactFrom.getAddress());
        pContactTo.setBirthDate(pContactFrom.getBirthDate());
        pContactTo.setOccupation(pContactFrom.getOccupation());
    }

    private static class ContactStorageHolder {
        private final static VirtualContactStorage instance = new VirtualContactStorage();
    }

    public static VirtualContactStorage getInstance() {
        return ContactStorageHolder.instance;
    }

}
