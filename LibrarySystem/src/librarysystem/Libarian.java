package librarysystem;

import java.util.Objects;

public class Libarian {
    private int id;
    private String name, contactInfo, password;

    public Libarian(int id, String name, String contactInfo, String password) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.password = password;
    }

    public Libarian() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Libarian{" + "id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Libarian)) {
            return false;
        }
        Libarian libarian = (Libarian) obj;
        return id == libarian.id
                && Objects.equals(name, libarian.name)
                && Objects.equals(contactInfo, libarian.contactInfo)
                && Objects.equals(password, libarian.password);
    }
    
    public void addLibraryItem(){
        
    }
    
    public void removeLibraryItem(){
        
    }
    
    public void updateLibraryItem(){
        
    }
    
    public void manageUser(){
        
    }
    
    public void trackBorrowings(){
        
    }
    
    private List<User> users(){
        
    }
}
