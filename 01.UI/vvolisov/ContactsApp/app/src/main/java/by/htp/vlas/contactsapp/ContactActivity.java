package by.htp.vlas.contactsapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 02.02.2015.
 */
public class ContactActivity extends Activity {

//    private static Contact contact = new Contact();
////    private static final DateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//    static {
//        Calendar sCalendar = Calendar.getInstance();
//        sCalendar.set(1990, 10, 1);
//        contact.setName("Ivan Ivanov");
//        contact.setPhone("7775533");
//        contact.setAddress("Skoriny 58");
//        contact.setEmail("ivan@gmail.com");
//        contact.setBirthDate(sCalendar.getTime());
//        contact.setOccupation("Android Developer");
//    }

    @InjectView(R.id.name)
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

    ContactStorage mContactStorage = new ContactStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.inject(this);

        Contact contact = mContactStorage.getById(getIntent().getIntExtra(EXTRA_CONTACT_POSITION, 0));

        setDataToViews(contact);
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
}
