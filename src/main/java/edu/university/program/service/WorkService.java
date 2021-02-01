package edu.university.program.service;

import edu.university.program.model.Work;

import java.util.List;

public interface WorkService {
    Work create(Work work);
    Work readById(long id);
    Work update(Work work);
    void delete(long id);

    List<Work> getAll();
    List<Work> getByWorkId(long workId);

}
