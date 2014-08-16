// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.AbstractPerson;
import com.mycompany.school.domain.PersonRole;
import com.mycompany.school.reference.Gender;
import java.util.Date;
import java.util.Set;

privileged aspect AbstractPerson_Roo_JavaBean {
    
    public String AbstractPerson.getFirstName() {
        return this.firstName;
    }
    
    public void AbstractPerson.setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String AbstractPerson.getLastName() {
        return this.lastName;
    }
    
    public void AbstractPerson.setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Gender AbstractPerson.getGender() {
        return this.gender;
    }
    
    public void AbstractPerson.setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String AbstractPerson.getFatherName() {
        return this.fatherName;
    }
    
    public void AbstractPerson.setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    
    public String AbstractPerson.getMotherName() {
        return this.motherName;
    }
    
    public void AbstractPerson.setMotherName(String motherName) {
        this.motherName = motherName;
    }
    
    public Date AbstractPerson.getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void AbstractPerson.setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String AbstractPerson.getEmailId() {
        return this.emailId;
    }
    
    public void AbstractPerson.setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public String AbstractPerson.getAddress() {
        return this.address;
    }
    
    public void AbstractPerson.setAddress(String address) {
        this.address = address;
    }
    
    public String AbstractPerson.getCity() {
        return this.city;
    }
    
    public void AbstractPerson.setCity(String city) {
        this.city = city;
    }
    
    public String AbstractPerson.getCountryState() {
        return this.countryState;
    }
    
    public void AbstractPerson.setCountryState(String countryState) {
        this.countryState = countryState;
    }
    
    public String AbstractPerson.getCountry() {
        return this.country;
    }
    
    public void AbstractPerson.setCountry(String country) {
        this.country = country;
    }
    
    public String AbstractPerson.getMobile() {
        return this.mobile;
    }
    
    public void AbstractPerson.setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String AbstractPerson.getPincode() {
        return this.pincode;
    }
    
    public void AbstractPerson.setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public boolean AbstractPerson.isEnabled() {
        return this.enabled;
    }
    
    public void AbstractPerson.setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public String AbstractPerson.getPasswordSHA() {
        return this.passwordSHA;
    }
    
    public void AbstractPerson.setPasswordSHA(String passwordSHA) {
        this.passwordSHA = passwordSHA;
    }
    
    public Set<PersonRole> AbstractPerson.getPersonRoles() {
        return this.personRoles;
    }
    
    public void AbstractPerson.setPersonRoles(Set<PersonRole> personRoles) {
        this.personRoles = personRoles;
    }
    
}