package POJOs;


import java.sql.Date;

public class Borrow {
    private String SSN;
    private String bookID;
    private String title;
    private Date borrowDate;
    private Date lastDate;
    private Date returnDate;



    public Borrow(){}

    public Borrow(String SSN, String bookID, Date borrowDate, Date lastDate, Date returnDate) {
        this.SSN = SSN;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.lastDate = lastDate;
        this.returnDate = returnDate;
    }

    public String getSSN() {
        return SSN;
    }

    public String getBookID() {
        return bookID;
    }



    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }





    public Borrow(String title, Date borrowDate, Date lastDate, Date returnDate) {
        this.title = title;
        this.borrowDate = borrowDate;
        this.lastDate = lastDate;
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
