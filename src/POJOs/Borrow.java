package POJOs;


import java.sql.Date;

public class Borrow {
    private String SSN;
    private String bookID;
    private String title;
    private Date borrowDate;
    private Date lastDate;
    private Date returnDate;

    public class otherBorrowDetail{

        private String SSN;
        private String fullName;
        private String returnDate;
        private String lastDate;
        private String borrowDate;

        public otherBorrowDetail() {
        }

        public String getSSN() {
            return SSN;
        }

        public void setSSN(String SSN) {
            this.SSN = SSN;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
        }

        public String getLastDate() {
            return lastDate;
        }

        public void setLastDate(String lastDate) {
            this.lastDate = lastDate;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(String borrowDate) {
            this.borrowDate = borrowDate;
        }
    }

   public class BorrowDetail{

        private String title;
        private String genre;
        private String pageNumber;
        private String publisher;
        private String publishedDate;
        private String authorID;
        private String authorFullName;
        private String fullName;
        private String address;
        private String birthDate;
        private String userName;
        private String pass;


       public BorrowDetail() {
       }

       public BorrowDetail(String title, String genre, String pageNumber, String publisher, String publishedDate, String authorID, String authorFullName, String fullName, String birthDate, String userName, String pass) {
           this.title = title;
           this.genre = genre;
           this.pageNumber = pageNumber;
           this.publisher = publisher;
           this.publishedDate = publishedDate;
           this.authorID = authorID;
           this.authorFullName = authorFullName;
           this.fullName = fullName;
           this.birthDate = birthDate;
           this.userName = userName;
           this.pass = pass;
       }

       public String getAddress() {
           return address;
       }

       public void setAddress(String address) {
           this.address = address;
       }

       public String getTitle() {
           return title;
       }

       public void setTitle(String title) {
           this.title = title;
       }

       public String getGenre() {
           return genre;
       }

       public void setGenre(String genre) {
           this.genre = genre;
       }

       public String getPageNumber() {
           return pageNumber;
       }

       public void setPageNumber(String pageNumber) {
           this.pageNumber = pageNumber;
       }

       public String getPublisher() {
           return publisher;
       }

       public void setPublisher(String publisher) {
           this.publisher = publisher;
       }

       public String getPublishedDate() {
           return publishedDate;
       }

       public void setPublishedDate(String publishedDate) {
           this.publishedDate = publishedDate;
       }

       public String getAuthorID() {
           return authorID;
       }

       public void setAuthorID(String authorID) {
           this.authorID = authorID;
       }

       public String getAuthorFullName() {
           return authorFullName;
       }

       public void setAuthorFullName(String authorFullName) {
           this.authorFullName = authorFullName;
       }

       public String getFullName() {
           return fullName;
       }

       public void setFullName(String fullName) {
           this.fullName = fullName;
       }

       public String getBirthDate() {
           return birthDate;
       }

       public void setBirthDate(String birthDate) {
           this.birthDate = birthDate;
       }

       public String getUserName() {
           return userName;
       }

       public void setUserName(String userName) {
           this.userName = userName;
       }

       public String getPass() {
           return pass;
       }

       public void setPass(String pass) {
           this.pass = pass;
       }
   }


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
