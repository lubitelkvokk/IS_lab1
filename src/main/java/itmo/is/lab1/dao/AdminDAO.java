package itmo.is.lab1.dao;

import itmo.is.lab1.model.auth.AdminRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface AdminDAO extends JpaRepository<AdminRequests, Integer> {

    @Query("select ar from admin_requests ar where ar.date >= :creationDateTime and ar.accepted = :bool")
    Collection<AdminRequests> findAllByAcceptedWithDate(@Param("bool") Boolean bool, @Param("creationDateTime") Date date);

}
