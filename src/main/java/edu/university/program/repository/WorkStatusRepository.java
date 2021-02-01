package edu.university.program.repository;

import edu.university.program.model.WorkStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkStatusRepository extends JpaRepository<WorkStatus, Long> {
}
