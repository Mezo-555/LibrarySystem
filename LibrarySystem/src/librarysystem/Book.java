package librarysystem;

import java.util.Objects;

public class Book extends LibraryItem{
    private String ISBN;
    private String publisher;
    private int publishYear;
    private int numberOfPage;
    private int edition;

    public Book(String ISBN, String publisher, String author, int publishYear, int numberOfPage, int edition, int id, String title, String category, Status s) {
        super(id, title, author, category, s);
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
    public String getTitle() {
        return super.getTitle();
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
    public void setTitle(String tittle) {
        super.setTitle(tittle);
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
    public String toString() {
        return super.toString() + "\nBook{" + "ISBN=" + ISBN + ", publisher=" + publisher + ", publishYear=" + publishYear + ", numberOfPage=" + numberOfPage + ", edition=" + edition + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book book = (Book) obj;
        return super.equals(obj) &&
                Objects.equals(ISBN, book.ISBN) &&
                publishYear == book.publishYear &&
                numberOfPage == book.numberOfPage &&
                Objects.equals(publisher, book.publisher) &&
                edition == book.edition;
    }

    
    
}
