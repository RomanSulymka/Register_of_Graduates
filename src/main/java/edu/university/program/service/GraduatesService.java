package edu.university.program.service;

import edu.university.program.model.Graduates;

import java.util.List;

public interface GraduatesService {
    Graduates create (Graduates graduates);
    Graduates update (Graduates graduates);
    Graduates readById (long id);
    void delete (long id);
    List<Graduates> getAll();
}
