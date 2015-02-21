package by.htp.vlas.contactsapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by VlasEL on 17.02.2015 19:03
 */
public class ContactStorage {

    private List<Contact> contactsList = new ArrayList<>();

    //generate random contacts data
    {
        for (int i = 0; i < 100; i++) {
            saveOrUpdate(new Contact(
                    String.valueOf((int) ((Math.random() + 1) * 1111111))
                    , "Name " + i
                    , "mail" + i + "@server"
                    , "Street " + i
                    , new Date(1995 + i, 10, 15)
                    , "user " + i
            ));
        }
    }

    private ContactStorage() {
    }

    public List<Contact> list() {
        return contactsList;
    }

    public Contact get(int pId) {
        if (pId < 0 || pId >= contactsList.size()) {
            return null;
        }
        return contactsList.get(pId);
    }

    /**
     * Save new contact in persistence storage, if pContact.id = null, <br>
     *     or update contact, existing in persistence storage with id = pContact.id
     * @param pContact contact to saveOrUpdate or update
     * @return persistent Contact object with id
     * @throws java.lang.NullPointerException if pContact == null
     */
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
        private final static ContactStorage instance = new ContactStorage();
    }

    public static ContactStorage getInstance() {
        return ContactStorageHolder.instance;
    }

}
