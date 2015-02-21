package by.htp.vlas.contactsapp.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.htp.vlas.contactsapp.Contact;

/**
 * Created by VlasEL on 17.02.2015 19:03
 */
public interface ContactStorage {

    List<Contact> list();

    Contact get(int pId);

    /**
     * Save new contact in persistence storage, if pContact.id = null, <br>
     * or update contact, existing in persistence storage with id = pContact.id
     *
     * @param pContact contact to saveOrUpdate or update
     * @return persistent Contact object with id
     * @throws java.lang.NullPointerException if pContact == null
     */
    Contact saveOrUpdate(Contact pContact);

    void delete(Contact pContact);

}
