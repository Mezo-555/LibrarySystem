package librarysystem;

import java.util.Objects;

public class Journal extends LibraryItem{
    private String ISSN, publicationFrequency, publisher;
    final String type = "Journal";

    public Journal(String ISSN, String publisher, String publicationFrequency, String title, String author, String category, Status s) {
        super(title, author, category, s);
        this.ISSN = ISSN;
        this.publisher = publisher;
        this.publicationFrequency = publicationFrequency;
    }

    public Journal() {
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public String getPublicationFrequency() {
        return publicationFrequency;
    }

    public void setPublicationFrequency(String publicationFrequency) {
        this.publicationFrequency = publicationFrequency;
    }

    @Override
    public String toString() {
        return "Journal{" + "ISSN=" + ISSN + ", publicationFrequency=" + publicationFrequency + ", publisher=" + publisher + '}';
    }
    
    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Journal)) {
            return false;
        }
        Journal journal = (Journal) obj;
        return super.equals(obj) &&
                Objects.equals(ISSN, journal.ISSN) &&
                Objects.equals(publisher, journal.publisher) &&
                Objects.equals(publicationFrequency, journal.publicationFrequency);
    }
}
