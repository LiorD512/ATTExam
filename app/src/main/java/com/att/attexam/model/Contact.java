package com.att.attexam.model;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {

    private String mFirstName;
    private String mLastName;
    private String mAddress;
    private String mEmail;
    private String mPhoneNumber;

    public Contact(String firstName, String lastName, String address, String email, String phoneNumber) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mAddress = address;
        this.mEmail = email;
        this.mPhoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(mFirstName, contact.mFirstName) &&
                Objects.equals(mLastName, contact.mLastName) &&
                Objects.equals(mAddress, contact.mAddress) &&
                Objects.equals(mEmail, contact.mEmail) &&
                Objects.equals(mPhoneNumber, contact.mPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mFirstName, mLastName, mAddress, mEmail, mPhoneNumber);
    }
}
