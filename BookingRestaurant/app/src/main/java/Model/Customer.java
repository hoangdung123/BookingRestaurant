package Model;

public class Customer {
    private String Address;
    private String phoneNumber;
    private String fullName;

    public Customer() {
    }

    public Customer(String address, String phoneNumber, String fullName) {
        Address = address;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
