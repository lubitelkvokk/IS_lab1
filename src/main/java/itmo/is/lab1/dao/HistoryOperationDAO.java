package itmo.is.lab1.dao;

import itmo.is.lab1.model.additional.HistoryOperation;
import itmo.is.lab1.model.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryOperationDAO extends JpaRepository<HistoryOperation, Integer> {


    Page<HistoryOperation> findAllByUser(User user, Pageable pageable);
}
