package by.htp.vlas.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import contacts.vlas.htp.by.contactsapp.R;

import static by.htp.vlas.contactsapp.ContactActivityRead.EXTRA_CONTACT_POSITION;

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
                Intent intent = new Intent(ContactListActivity.this, ContactActivityRead.class);
                intent.putExtra(EXTRA_CONTACT_POSITION, position);
                startActivity(intent);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                Toast.makeText(this, "action ADD", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_remove:
                Toast.makeText(this, "action REMOVE", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_clear:
                Toast.makeText(this, "action CLEAR", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
