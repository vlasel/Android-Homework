package by.htp.vlas.contactsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 02.02.2015.
 */
public class ContactActivityEdit extends Activity {

    @InjectView(R.id.nameRead)
    TextView mNameView;

    @InjectView(R.id.phone)
    TextView mPhoneView;

    @InjectView(R.id.email)
    TextView mEmailView;

    @InjectView(R.id.address)
    TextView mAddressView;

    @InjectView(R.id.birthdate)
    TextView mBirthDateView;

    @InjectView(R.id.occupation)
    TextView mOccupationView;

    public static final String EXTRA_CONTACT_POSITION = "contact_position";
    public static final String EXTRA_CONTACT_CHANGED = "contact_changed";

    ContactStorage mContactStorage = ContactStorage.getInstance();
    Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        ButterKnife.inject(this);
        setTitle(getString(R.string.title_activity_contact_edit));

        int mContactPosition = getIntent().getIntExtra(EXTRA_CONTACT_POSITION, 0);
        mContact = mContactStorage.get(mContactPosition);
        setDataToViews(mContact);
    }

    @OnClick(R.id.btn_save)
    void btnSaveActin() {
        updateContactFromViewsData(mContact);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CONTACT_CHANGED, true);
        intent.putExtra(EXTRA_CONTACT_POSITION, mContact.getId());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setDataToViews(Contact contact){
        if(contact == null) {
            return;
        }
        mNameView.setText(contact.getName());
        mPhoneView.setText(contact.getPhone());
        mEmailView.setText(contact.getEmail());
        mAddressView.setText(contact.getAddress());
        mBirthDateView.setText(DateFormat.getDateFormat(this).format(contact.getBirthDate()));
        mOccupationView.setText(contact.getOccupation());
    }

    private void updateContactFromViewsData(Contact pContact){
        if(pContact == null) {
            return;
        }
        Date mBirthDate = null;
        try {
            mBirthDate = DateFormat.getDateFormat(this).parse(mBirthDateView.getText().toString());
        } catch (ParseException e) {
            Toast.makeText(this, getString(R.string.error_date_format), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        pContact.setName(mNameView.getText().toString());
        pContact.setPhone(mPhoneView.getText().toString());
        pContact.setEmail(mEmailView.getText().toString());
        pContact.setAddress(mAddressView.getText().toString());
        pContact.setBirthDate(mBirthDate);
        pContact.setOccupation(mOccupationView.getText().toString());
    }

}
