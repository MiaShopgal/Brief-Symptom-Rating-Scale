package tw.org.tsos.bsrs.util.db.bean;

@SuppressWarnings("UnusedDeclaration")
public class Resource {

    private String name;
    private String address;
    private String area;
    private String county;
    private int zip;
    private String phone;

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                ", county='" + county + '\'' +
                ", zip=" + zip +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
