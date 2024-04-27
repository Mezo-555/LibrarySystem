package librarysystem;

import java.util.Objects;

public abstract class LibraryItem {
    private int id;
    private String tittle;
    private String category;
    private Status s;

    public LibraryItem(int id, String tittle, String category, Status s) {
        this.id = id;
        this.tittle = tittle;
        this.category = category;
        this.s = s;
    }

    public LibraryItem() {
    }
    
    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getCategory() {
        return category;
    }

    public Status getS() {
        return s;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setS(Status s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "LibraryItem{" + "id=" + id + ", tittle=" + tittle + ", category=" + category + ", s=" + s + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LibraryItem other = (LibraryItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.tittle, other.tittle)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (this.s != other.s) {
            return false;
        }
        return true;
    }
    
    public abstract void add(String item);
    public abstract void remove(String item);
    public abstract void update(String item);
}
