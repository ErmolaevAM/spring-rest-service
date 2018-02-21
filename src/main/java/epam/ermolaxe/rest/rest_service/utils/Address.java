package epam.ermolaxe.rest.rest_service.utils;

public class Address {

    private String street;

    private Integer buildNumber;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", street, buildNumber);
    }
}
