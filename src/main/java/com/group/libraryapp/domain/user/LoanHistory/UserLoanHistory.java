package com.group.libraryapp.domain.user.LoanHistory;

import com.group.libraryapp.domain.user.User;
import jakarta.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;


    protected UserLoanHistory() {

    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public String getBookName() {
        return bookName;
    }

    public void doReturn(){
        this.isReturn = true;
    }
}
