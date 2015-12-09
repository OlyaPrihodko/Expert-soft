package com.expert_soft.prihodko.task.entity;

public class Contact {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String phone;
    public Contact(){

    }
    public Contact(String name, String surname, String login, String email, String phone){
        this.name=name;
        this.surname=surname;
        this.login=login;
        this.email=email;
        this.phone=phone;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        if (id != contact.id) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (login != null ? !login.equals(contact.login) : contact.login != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
        if (surname != null ? !surname.equals(contact.surname) : contact.surname != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 3 * result + (name != null ? name.hashCode() : 0);
        result = 5 * result + (surname != null ? surname.hashCode() : 0);
        result = 7 * result + (login != null ? login.hashCode() : 0);
        result = 11 * result + (email != null ? email.hashCode() : 0);
        result = 13 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return
                "<td>"+this.getName()+"</td>"+
                        "<td>"+this.getSurname()+"</td>"+
                        "<td>"+this.getLogin()+"</td>"+
                        "<td>"+this.getEmail()+"</td>"+
                        "<td>"+this.getPhone()+"</td>";
    }
}
