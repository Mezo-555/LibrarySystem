package librarysystem;

import java.util.Objects;

public class Journal extends LibraryItem{
    private String ISSN, publicationFrequency;

    public Journal(String ISSN, String publicationFrequency, int id, String title, String author, String category, Status s) {
        super(id, title, author, category, s);
        this.ISSN = ISSN;
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

    public String getPublicationFrequency() {
        return publicationFrequency;
    }

    public void setPublicationFrequency(String publicationFrequency) {
        this.publicationFrequency = publicationFrequency;
    }

    @Override
    public String toString() {
        return super.toString() + "Journal{" + "ISSN=" + ISSN + ", publicationFrequency=" + publicationFrequency + '}';
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
                Objects.equals(publicationFrequency, journal.publicationFrequency);
    }
}
