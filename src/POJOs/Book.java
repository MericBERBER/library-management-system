package POJOs;


import java.sql.Date;

public class Book {
    private String bookId;
    private String Title;
    private String Genre;
    private int PageNumber;
    private String Publisher;
    private Date PublishDate;
    private int piece;
    private String Author;

    public class UserBook {

        private String bookId;
        private String Title;
        private String Genre;
        private String borrowDate;
        private String returnDate;

        public UserBook() {
        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(String borrowDate) {
            this.borrowDate = borrowDate;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
        }
    }

    public class OtherBook{

        private String bookId;
        private String Title;
        private String Genre;
        private String Times;

        public OtherBook(){

        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            Genre = genre;
        }

        public String getTimes() {
            return Times;
        }

        public void setTimes(String times) {
            Times = times;
        }
    }
    public Book() {}

    public Book(String bookId, String title, String genre, int pageNumber, String publisher, Date publishDate, int piece, String author) {
        this.bookId = bookId;
        Title = title;
        Genre = genre;
        PageNumber = pageNumber;
        Publisher = publisher;
        PublishDate = publishDate;
        this.piece = piece;
        Author = author;
    }

    public Book(String title, String genre, int pageNumber, String publisher, Date publishDate, String author) {
        Title = title;
        Genre = genre;
        PageNumber = pageNumber;
        Publisher = publisher;
        PublishDate = publishDate;
        Author = author;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
