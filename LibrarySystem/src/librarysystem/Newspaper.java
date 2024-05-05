package librarysystem;

import java.util.Objects;

public class Newspaper extends LibraryItem {
    private String publicationFrequency, publisher;

    public Newspaper(String publicationFrequency, String publisher, int id, String title, String author, String category, Status s) {
        super(id, title, author, category, s);
        this.publicationFrequency = publicationFrequency;
        this.publisher = publisher;
    }

    public Newspaper() {
    }

    public String getPublicationFrequency() {
        return publicationFrequency;
    }

    public void setPublicationFrequency(String publicationFrequency) {
        this.publicationFrequency = publicationFrequency;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return super.toString() + "Newspaper{" + "publicationFrequency=" + publicationFrequency + ", publisher=" + publisher + '}';
    }
    
    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Newspaper)) {
            return false;
        }
        Newspaper newspaper = (Newspaper) obj;
        return super.equals(obj) &&
                Objects.equals(publisher, newspaper.publisher) &&
                Objects.equals(publicationFrequency, newspaper.publicationFrequency);
    }
}
