package com.example.demo.entity;

import java.util.List;

public class TrueCallerDataDto {

    private String id;

    private String name;

    private String firstName;

    private String lastName;

    private String gender;

    private String image;

    private Double score;

    private String access;

    private Boolean enhanced;

    private List<String> badges;

//    private List<TrueCallerPhoneDto> phones;
//
//    private List<TreuCallerInternetAddressDto> internetAddresses;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Boolean getEnhanced() {
        return enhanced;
    }

    public void setEnhanced(Boolean enhanced) {
        this.enhanced = enhanced;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

//    public List<TrueCallerPhoneDto> getPhones() {
//        return phones;
//    }
//
//    public void setPhones(List<TrueCallerPhoneDto> phones) {
//        this.phones = phones;
//    }
//
//    public List<TreuCallerInternetAddressDto> getInternetAddresses() {
//        return internetAddresses;
//    }
//
//    public void setInternetAddresses(List<TreuCallerInternetAddressDto> internetAddresses) {
//        this.internetAddresses = internetAddresses;
//    }

}
