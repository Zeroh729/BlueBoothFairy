package android.zeroh729.com.blueboothfairy.buyers.data.model;

public class ContactProfile {
    public String displayName;
    public String mobileNumber;
    public String email;
    public String phoneticName;
    public String jobTitle;
    public String address;
    public String website;

    public ContactProfile(String displayName, String mobileNumber) {
        this.displayName = displayName;
        this.mobileNumber = mobileNumber;
    }
}
