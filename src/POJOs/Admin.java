package POJOs;

import java.sql.Date;

public class Admin {

    private String SSN;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String StreetName;
    private String zipCode;
    private String city;
    private String adminUserName;
    private String adminPassword;

    public Admin(){}

    public Admin(String SSN, String firstName, String lastName, Date birthDate, String streetName, String zipCode, String city, String adminUserName, String adminPassword) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        StreetName = streetName;
        this.zipCode = zipCode;
        this.city = city;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
