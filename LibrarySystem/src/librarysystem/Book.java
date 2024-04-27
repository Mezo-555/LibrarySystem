package librarysystem;

import java.util.Objects;

public class Book extends LibraryItem{
    private String ISBN;
    private String publisher;
    private int publishYear;
    private int numberOfPage;
    private int edition;

    public Book(String ISBN, String publisher, int publishYear, int numberOfPage, int edition, int id, String tittle, String category, Status s) {
        super(id, tittle, category, s);
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.numberOfPage = numberOfPage;
        this.edition = edition;
    }

    public Book() {
    }
    
    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public int getEdition() {
        return edition;
    }
    
    @Override
    public String getTittle() {
        return super.getTittle();
    }

    @Override
    public Status getS() {
        return super.getS();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getCategory() {
        return super.getCategory();
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }
    
    @Override
    public void setTittle(String tittle) {
        super.setTittle(tittle);
    }

    @Override
    public void setS(Status s) {
        super.setS(s);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setCategory(String category) {
        super.setCategory(category);
    }
    @Override
    public void add(String item) {
    /////TBD
    }

    @Override
    public void remove(String item) {
    /////TBD
    }

    @Override
    public void update(String item) {
    /////TBD
    }

    @Override
    public String toString() {
        return super.toString() + "\nBook{" + "ISBN=" + ISBN + ", publisher=" + publisher + ", publishYear=" + publishYear + ", numberOfPage=" + numberOfPage + ", edition=" + edition + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        super.equals(obj);
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.publishYear != other.publishYear) {
            return false;
        }
        if (this.numberOfPage != other.numberOfPage) {
            return false;
        }
        if (this.edition != other.edition) {
            return false;
        }
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        return true;
    }

    
    
}
