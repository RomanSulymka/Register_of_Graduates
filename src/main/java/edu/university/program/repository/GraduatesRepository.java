package edu.university.program.repository;

import edu.university.program.model.Graduates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraduatesRepository extends JpaRepository<Graduates, Long> {

    @Query(value = "select * from graduates where name = ?1", nativeQuery = true)
    Graduates getGraduatesByName(String name);

    @Query(value = "select * from graduates order by id", nativeQuery = true)
    List<Graduates> getAll();

}
