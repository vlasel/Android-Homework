package by.htp.vlas.contactsapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by VlasEL on 17.02.2015 19:03
 */
public class ContactStorage {

    private static List<Contact> sContacts = new ArrayList<>();

    static {
        for (int i = 0; i < 30; i++) {
            sContacts.add(new Contact(
                    String.valueOf((int) ((Math.random() + 1) * 1111111))
                    , "Name" + i
                    , "mail" + i + "@server"
                    , "Street" + i
                    , new Date(1995 + i, 10, 15)
                    , "user" + i
            ));
        }
    }

    public List<Contact> getContacts() {
        return sContacts;
    }

    public Contact getById(int pId) {
        Contact contact = null;
        if(pId >=0 && pId < sContacts.size()) {
            contact = sContacts.get(pId);
        }
        return contact;
    }

}
