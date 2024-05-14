package librarysystem;

import java.util.Date;

public class BorrowingDetails {
    private String itemID;
    private String userID;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;

    // Constructor
    public BorrowingDetails(String itemID, String userID, Date borrowDate, Date dueDate, Date returnDate) {
        this.itemID = itemID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public BorrowingDetails() {
    }

    // Getters and setters
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
