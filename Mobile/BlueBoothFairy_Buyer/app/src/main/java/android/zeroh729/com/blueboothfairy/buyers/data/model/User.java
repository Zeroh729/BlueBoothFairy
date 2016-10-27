package android.zeroh729.com.blueboothfairy.buyers.data.model;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class User {
    private String id = "user0";
    private String name = "Jerome";
    private String contactNumber = "0916329092";
    private String email = "jerome@yahoo.com";

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
