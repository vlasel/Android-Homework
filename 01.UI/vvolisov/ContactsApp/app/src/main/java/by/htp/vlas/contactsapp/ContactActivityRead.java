package by.htp.vlas.contactsapp;

import static by.htp.vlas.contactsapp.ContactActivityEdit.EXTRA_CONTACT_CHANGED;
import static by.htp.vlas.contactsapp.ContactListActivity.INTENT_REQUEST_CONTACT_READ_EDIT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 02.02.2015.
 */
public class ContactActivityRead extends Activity {

    @InjectView(R.id.nameRead)
    TextView mNameView;

    @InjectView(R.id.phoneRead)
    TextView mPhoneView;

    @InjectView(R.id.emailRead)
    TextView mEmailView;

    @InjectView(R.id.addressRead)
    TextView mAddressView;

    @InjectView(R.id.birthdateRead)
    TextView mBirthDateView;

    @InjectView(R.id.occupationRead)
    TextView mOccupationView;

    public static final String EXTRA_CONTACT_POSITION = "contact_position";
//    public static final int INTENT_REQUEST_CONTACT_EDIT = Math.abs("contact_edit_request".hashCode());

//    private int intentForTransitResultRequestCode;

    ContactStorage mContactStorage = ContactStorage.getInstance();
    Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_read);
        ButterKnife.inject(this);
        setTitle(getString(R.string.title_activity_contact_read));

        setEditable(false);

        int mContactPosition = getIntent().getIntExtra(EXTRA_CONTACT_POSITION, 0);
        mContact = mContactStorage.get(mContactPosition);
        renewDataInViews(mContact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_contact_read, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(this, ContactActivityEdit.class);
                intent.putExtra(EXTRA_CONTACT_POSITION, mContact.getId());
//                startActivityForResult(intent, INTENT_REQUEST_CONTACT_EDIT);
                startActivityForResult(intent, INTENT_REQUEST_CONTACT_READ_EDIT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == INTENT_REQUEST_CONTACT_EDIT && resultCode == RESULT_OK) {
//            boolean isDataChanged = data.getBooleanExtra(EXTRA_CONTACT_CHANGED, true);
//            if (isDataChanged) {
//                notifyListViewAdapterDataSetChanged();
//                int contactPosition = getIntent().getIntExtra(EXTRA_CONTACT_POSITION, 0);
//                //check if returns right object id (the same is the position in ListView)
//                if (mContact.getId() == contactPosition) {
//                    renewDataInViews(mContactStorage.get(contactPosition));
//
//                }
//            }
//        }
        if (requestCode == INTENT_REQUEST_CONTACT_READ_EDIT) {
            setResult(resultCode, data);
            finish();
        }
    }

    private void notifyListViewAdapterDataSetChanged() {
        ListView listView = (ListView) findViewById(android.R.id.list);
        BaseAdapter adapter = (ContactAdapter) listView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void renewDataInViews(Contact contact) {
        if (contact == null) {
            return;
        }
        mNameView.setText(contact.getName());
        mPhoneView.setText(contact.getPhone());
        mEmailView.setText(contact.getEmail());
        mAddressView.setText(contact.getAddress());
        mBirthDateView.setText(DateFormat.getDateFormat(this).format(contact.getBirthDate()));
        mOccupationView.setText(contact.getOccupation());
    }

    private void setEditable(boolean isEditable) {
        if (!isEditable) {
            mNameView.setKeyListener(null);
            mPhoneView.setKeyListener(null);
            mEmailView.setKeyListener(null);
            mAddressView.setKeyListener(null);
            mBirthDateView.setKeyListener(null);
            mOccupationView.setKeyListener(null);
        }
    }
}
