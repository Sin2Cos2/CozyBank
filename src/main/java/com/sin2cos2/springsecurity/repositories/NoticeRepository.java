package com.sin2cos2.springsecurity.repositories;

import com.sin2cos2.springsecurity.models.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = "from Notice n where CURRENT_DATE BETWEEN n.noticBegDt AND n.noticEndDt")
    List<Notice> findAllActiveNotices();
}
