package edu.university.program.repository;

import edu.university.program.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query(value = "select * from works where graduates_id = ?1", nativeQuery = true)
    List<Work> getByWorkId(long todoId);

    @Query(value = "select * from works order by id", nativeQuery = true)
    List<Work> getAll();
}
