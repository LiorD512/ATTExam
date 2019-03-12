package com.att.attexam.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.att.attexam.R;
import com.att.attexam.model.Contact;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {

    private Contact mContact;


    public ContactDetailFragment() {
        // Required empty public constructor
    }

    public static ContactDetailFragment newInstance(Contact contact) {
        ContactDetailFragment myFragment = new ContactDetailFragment();

        Bundle args = new Bundle();
        args.putSerializable("contact", contact);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.detail_fragment_contact_name);
        TextView email = view.findViewById(R.id.detail_fragment_contact_email);
        TextView address = view.findViewById(R.id.detail_fragment_contact_address);

        assert getArguments() != null;
        mContact = (Contact) getArguments().getSerializable("contact");

        if (mContact != null){
            name.setText(mContact.getFirstName() + " " + mContact.getLastName());
            email.setText(mContact.getEmail());
            address.setText(mContact.getAddress());
        }


    }
}
