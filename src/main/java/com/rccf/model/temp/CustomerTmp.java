package com.rccf.model.temp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerTmp {

    private String id;
    private String name;
    private String phone;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
