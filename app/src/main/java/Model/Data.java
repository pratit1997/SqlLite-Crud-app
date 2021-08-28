package Model;

public class Data {
    String Name,Address;
    String PhoneNumber,Snn;

    public Data() {
    }

    public Data(String snn) {
        Snn = snn;
    }

    public Data(String snn, String name, String phoneNumber, String address ) {
        Name = name;
        Address = address;
        PhoneNumber = phoneNumber;
        Snn = snn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSnn() {
        return Snn;
    }

    public void setSnn(String snn) {
        Snn = snn;
    }

    @Override
    public String toString() {
        return "Data{" +
                "Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", PhoneNumber=" + PhoneNumber +
                ", Snn=" + Snn +
                '}';
    }
}
