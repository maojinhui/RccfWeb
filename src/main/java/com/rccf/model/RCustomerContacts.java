package com.rccf.model;

import javax.persistence.*;

@Entity
@Table(name = "r_customer_contacts", schema = "rccf", catalog = "")
public class RCustomerContacts {
    private int id;
    private String customerId;
    private String contactName;
    private String contactRelationship;
    private String contactPhone;
    private String contacctAddress;
    private Integer contactIsLoan;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "contact_name")
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Basic
    @Column(name = "contact_relationship")
    public String getContactRelationship() {
        return contactRelationship;
    }

    public void setContactRelationship(String contactRelationship) {
        this.contactRelationship = contactRelationship;
    }

    @Basic
    @Column(name = "contact_phone")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "contacct_address")
    public String getContacctAddress() {
        return contacctAddress;
    }

    public void setContacctAddress(String contacctAddress) {
        this.contacctAddress = contacctAddress;
    }

    @Basic
    @Column(name = "contact_is_loan")
    public Integer getContactIsLoan() {
        return contactIsLoan;
    }

    public void setContactIsLoan(Integer contactIsLoan) {
        this.contactIsLoan = contactIsLoan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RCustomerContacts that = (RCustomerContacts) o;

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) return false;
        if (contactRelationship != null ? !contactRelationship.equals(that.contactRelationship) : that.contactRelationship != null)
            return false;
        if (contactPhone != null ? !contactPhone.equals(that.contactPhone) : that.contactPhone != null) return false;
        if (contacctAddress != null ? !contacctAddress.equals(that.contacctAddress) : that.contacctAddress != null)
            return false;
        if (contactIsLoan != null ? !contactIsLoan.equals(that.contactIsLoan) : that.contactIsLoan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (contactRelationship != null ? contactRelationship.hashCode() : 0);
        result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
        result = 31 * result + (contacctAddress != null ? contacctAddress.hashCode() : 0);
        result = 31 * result + (contactIsLoan != null ? contactIsLoan.hashCode() : 0);
        return result;
    }
}
