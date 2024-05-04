package librarysystem;

import java.util.Objects;

public class EBook extends Book {

    private String fileFormat;
    private int fileSize;

    public EBook(String fileFormat, int fileSize, String ISBN, String publisher, String author, int publishYear, int numberOfPage, int edition, int id, String title, String category, Status s) {
        super(ISBN, publisher, author, publishYear, numberOfPage, edition, id, title, category, s);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public EBook() {
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
    public String getPublisher() {
        return super.getPublisher();
    }

    @Override
    public int getPublishYear() {
        return super.getPublishYear();
    }

    @Override
    public int getNumberOfPage() {
        return super.getNumberOfPage();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getISBN() {
        return super.getISBN();
    }

    @Override
    public int getEdition() {
        return super.getEdition();
    }

    @Override
    public String getCategory() {
        return super.getCategory();
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public int getFileSize() {
        return fileSize;
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setS(Status s) {
        super.setS(s);
    }

    @Override
    public void setPublisher(String publisher) {
        super.setPublisher(publisher);
    }

    @Override
    public void setPublishYear(int publishYear) {
        super.setPublishYear(publishYear);
    }

    @Override
    public void setNumberOfPage(int numberOfPage) {
        super.setNumberOfPage(numberOfPage);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setISBN(String ISBN) {
        super.setISBN(ISBN);
    }

    @Override
    public void setEdition(int edition) {
        super.setEdition(edition);
    }

    @Override
    public void setCategory(String category) {
        super.setCategory(category);
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        super.toString();
        return "\nEBook{" + "fileFormat=" + fileFormat + ", fileSize=" + fileSize + '}';
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EBook)) {
            return false;
        }
        EBook ebook = (EBook) obj;
        return super.equals(obj) &&
                Objects.equals(fileFormat, ebook.fileFormat) &&
                Double.compare(ebook.fileSize, fileSize) == 0;
    }
}
