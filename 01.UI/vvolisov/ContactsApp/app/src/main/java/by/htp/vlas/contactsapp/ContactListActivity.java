package by.htp.vlas.contactsapp;

import static by.htp.vlas.contactsapp.ContactActivityRead.EXTRA_CONTACT_POSITION;
import static by.htp.vlas.contactsapp.ContactActivityEdit.EXTRA_CONTACT_CHANGED;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import by.htp.vlas.contactsapp.persistence.ContactStorage;
import by.htp.vlas.contactsapp.persistence.VirtualContactStorage;
import contacts.vlas.htp.by.contactsapp.R;



/**
 * Created by _guest on 06.02.2015.
 */
public class ContactListActivity extends Activity {

    public static final int INTENT_REQUEST_CONTACT_READ_EDIT = Math.abs("contact_read_edit_request".hashCode());

    private ContactStorage mContactStorage = VirtualContactStorage.getInstance();
    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        ListView listView = (ListView) findViewById(android.R.id.list);
        mAdapter = new ContactAdapter(mContactStorage.list());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactListActivity.this, ContactActivityRead.class);
                intent.putExtra(EXTRA_CONTACT_POSITION, position);
                startActivityForResult(intent, INTENT_REQUEST_CONTACT_READ_EDIT);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_REQUEST_CONTACT_READ_EDIT && resultCode == RESULT_OK) {
            boolean isDataChanged = data.getBooleanExtra(EXTRA_CONTACT_CHANGED, true);
            if (isDataChanged) {
                mAdapter.notifyDataSetChanged();
            }
        }
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
