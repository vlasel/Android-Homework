package by.htp.vlas.contactsapp;

import static by.htp.vlas.contactsapp.ContactActivity.EXTRA_CONTACT;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 06.02.2015.
 */
public class ContactListActivity extends Activity {

    ContactStorage mContactStorage = new ContactStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        List<Contact> contactList = mContactStorage.getContacts();

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new ContactAdapter(contactList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactListActivity.this, ContactActivity.class);
                intent.putExtra(EXTRA_CONTACT, id);
                startActivity(intent);
            }

        });

    }
}
