package edu.university.program.service.impl;

import edu.university.program.exception.NullEntityReferenceException;
import edu.university.program.model.Graduates;
import edu.university.program.model.User;
import edu.university.program.repository.GraduatesRepository;
import edu.university.program.service.GraduatesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GraduatesServiceImpl implements GraduatesService {

    private GraduatesRepository graduatesRepository;

    public GraduatesServiceImpl(GraduatesRepository graduatesRepository) {
        this.graduatesRepository = graduatesRepository;
    }

    @Override
    public Graduates create(Graduates graduates) {
        return graduatesRepository.save(graduates);
    }

    @Override
    public Graduates update(Graduates graduated) {
        if (graduated != null) {
            Graduates oldGraduated = readById(graduated.getId());
            if (oldGraduated != null) {
                return graduatesRepository.save(graduated);
            }
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public Graduates readById(long id) {
        Optional<Graduates> optional = graduatesRepository.findById(id);
        return optional.get();
    }

    @Override
    public void delete(long id) {
        Graduates graduates = readById(id);
        graduatesRepository.delete(graduates);
    }

    @Override
    public List<Graduates> getAll() {
        List<Graduates> graduates = graduatesRepository.findAll();
        return graduates.isEmpty() ? new ArrayList<>() : graduates;
    }

    @Override
    public Page<Graduates> findAll(Pageable pageable) {
        return graduatesRepository.findAll(pageable);
    }
}
