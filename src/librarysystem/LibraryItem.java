package librarysystem;

import java.util.Objects;

public abstract class LibraryItem {

    private int id;
    private String title;
    private String author;
    private String category;
    private Status s;

    public LibraryItem(String title, String author, String category, Status s) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.s = s;
    }

    public LibraryItem() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setS(Status s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "LibraryItem{" + "id=" + id + ", title=" + title + ", author=" + author + ", category=" + category + ", s=" + s + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LibraryItem that = (LibraryItem) obj;
        return id == that.id
                && Objects.equals(title, that.title)
                && Objects.equals(author, that.author)
                && Objects.equals(category, that.category)
                && Objects.equals(s, that.s);
    }
}
