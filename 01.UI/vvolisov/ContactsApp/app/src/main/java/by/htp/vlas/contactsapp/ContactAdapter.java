package by.htp.vlas.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import contacts.vlas.htp.by.contactsapp.R;

/**
 * Created by _guest on 06.02.2015.
 */
public class ContactAdapter extends BaseAdapter {

    List<Contact> mContactList;
//    ViewHolder mViewHolder;

    TextView mNameView;
    TextView mPhoneView;

    public ContactAdapter(List<Contact> pContactList) {
        mContactList = pContactList;
    }

    @Override
    public int getCount() {
        return mContactList.size();
    }

    @Override
    public Contact getItem(int pPosition) {
        return mContactList.get(pPosition);
    }

    @Override
    public long getItemId(int pPosition) {
        return mContactList.get(pPosition).getId();
    }

    @Override
    public View getView(int pPosition, View pConvertView, ViewGroup pParent) {
        View itemView;
        if (pConvertView == null) {
            itemView = LayoutInflater.from(pParent.getContext()).inflate(R.layout.activity_item_contact, pParent, false);

//            mViewHolder = new ViewHolder();
//            mViewHolder.nameView = (TextView) itemView.findViewById(R.id.act_item_cont_tw_name);
//            mViewHolder.phoneView = (TextView) itemView.findViewById(R.id.act_item_cont_tw_phone);

        } else {
            itemView = pConvertView;
        }

//        mViewHolder.nameView.setText(getItem(pPosition).getName());
//        mViewHolder.phoneView.setText(getItem(pPosition).getPhone());

        mNameView = (TextView) itemView.findViewById(R.id.act_item_cont_tw_name);
        mPhoneView = (TextView) itemView.findViewById(R.id.act_item_cont_tw_phone);

        mNameView.setText(getItem(pPosition).getName());
        mPhoneView.setText(getItem(pPosition).getPhone());

        return itemView;
    }

//    private class ViewHolder {
//        TextView nameView;
//        TextView phoneView;
//    }

}
