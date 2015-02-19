package by.htp.vlas.contactsapp;

import java.util.Date;

/**
 * Created by _guest on 02.02.2015.
 */
public class Contact {

    private static int sCounter = 0;

    private int mId;
    private String mPhone;
    private String mName;
    private String mEmail;
    private String mAddress;
    private Date mBirthDate;
    private String mOccupation;


    public Contact() {
        mId = ++sCounter;
    }

    public Contact(String phone, String name, String email, String address, Date birthDate, String occupation) {
        mId = ++sCounter;
        mPhone = phone;
        mName = name;
        mEmail = email;
        mAddress = address;
        mBirthDate = birthDate;
        mOccupation = occupation;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Date getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(Date birthDate) {
        mBirthDate = birthDate;
    }

    public String getOccupation() {
        return mOccupation;
    }

    public void setOccupation(String occupation) {
        mOccupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;
        return mId == contact.mId;

    }

    @Override
    public int hashCode() {
        return mId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "mPhone='" + mPhone + '\'' +
                ", mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mBirthDate=" + mBirthDate +
                ", mOccupation='" + mOccupation + '\'' +
                '}';
    }
}
