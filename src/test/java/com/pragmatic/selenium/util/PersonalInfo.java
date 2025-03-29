package com.pragmatic.selenium.util;

import net.datafaker.Faker;

public class PersonalInfo {

    private String firstName;
    private String lastName;
    private String postalCode;

    public PersonalInfo() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.postalCode = faker.address().postcode();

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
