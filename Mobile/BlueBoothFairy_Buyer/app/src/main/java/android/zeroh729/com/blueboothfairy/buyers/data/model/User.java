package android.zeroh729.com.blueboothfairy.buyers.data.model;

import org.androidannotations.annotations.EBean;

import java.util.HashMap;

@EBean(scope = EBean.Scope.Singleton)
public class User {
    private String id = "";
    private String name = "";
    private String contactNumber = "";
    private String email = "";

    public void setUser(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.contactNumber = user.getContactNumber();
        this.email = user.getEmail();
    }

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

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("contactno", contactNumber);
        map.put("email", email);
        return map;
    }
}
