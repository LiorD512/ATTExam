package com.att.attexam.views;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.HttpHandler;
import com.att.attexam.R;
import com.att.attexam.adapter.ContactListAdapter;
import com.att.attexam.interfaces.OnItemClickListener;
import com.att.attexam.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment implements OnItemClickListener {

    private ArrayList<Contact> mContactList;
    private static final String TAG = ContactListFragment.class.getSimpleName();
    private RecyclerView mContactRv;
    private GetContacts mGetContacts;


    public ContactListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContactRv = view.findViewById(R.id.contact_list_rv);

        mGetContacts = new GetContacts();
        mGetContacts.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mGetContacts != null && mGetContacts.getStatus() == AsyncTask.Status.RUNNING) {
            mGetContacts.cancel(true);
        }
    }

    @Override
    public void OnItemClickListener(int position) {
        Contact contact = mContactList.get(position);
        ContactDetailFragment contactDetailFragment= ContactDetailFragment.newInstance(contact);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, contactDetailFragment, ContactDetailFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();

    }

    public class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            mContactList = new ArrayList<>();
            do {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall("https://randomuser.me/api");

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("results");

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            JSONObject name = c.getJSONObject("name");
                            String firstName = name.getString("first");
                            String lastName = name.getString("last");
                            JSONObject location = c.getJSONObject("location");
                            String address = location.getString("street");
                            String email = c.getString("email");
                            String phone = c.getString("phone");


                            // adding contact to contact list
                            Contact co = new Contact(firstName,lastName,address,email,phone);
                            Log.d(TAG, "Co " + co.getFirstName());
                            mContactList.add(co);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");

                }
            }while (mContactList.size() < 10);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (!mGetContacts.isCancelled()){
                mContactRv.setAdapter(new ContactListAdapter(mContactList, ContactListFragment.this));
                mContactRv.setLayoutManager(new LinearLayoutManager(getContext()));
                mContactRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), RecyclerView.VERTICAL));
            }



        }
    }

}
