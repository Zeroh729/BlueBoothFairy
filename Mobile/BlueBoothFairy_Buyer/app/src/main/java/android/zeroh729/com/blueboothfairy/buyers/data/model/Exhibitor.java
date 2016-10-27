package android.zeroh729.com.blueboothfairy.buyers.data.model;

import java.util.ArrayList;

public class Exhibitor {
    private String id;
    private String name;
    private String description;
    private String website;
    private String contact_person;
    private String contact_person_role;
    private String email;
    private String address;
    private String imgUrl;
    private int imgRes;
    private String mobile;
    private ArrayList<String> products;

    public Exhibitor() {
        id = "";
        name = "";
        description = "";
        website = "";
        contact_person = "";
        contact_person_role = "";
        email = "";
        address = "";
        imgUrl = "";
        imgRes = 0;
        mobile = "";
        products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_person_role() {
        return contact_person_role;
    }

    public void setContact_person_role(String contact_person_role) {
        this.contact_person_role = contact_person_role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }
}
