package com.group.libraryapp.domain.user.LoanHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    // select * from user_loan_hostory where book_name = ? and is_return = ?
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

}
