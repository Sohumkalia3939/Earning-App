package com.whitegoldapps.cashm4;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
public class FriendsResponse {
    private String fName;
    private String email;
private String image;
    private int money;

    public FriendsResponse() {
    }

    public FriendsResponse(String fName, String email, String image, int money) {
        this.fName = fName;
        this.email = email;
        this.image = image;
        this.money = money;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
