package edu.university.program.service.impl;

import edu.university.program.exception.NullEntityReferenceException;
import edu.university.program.model.Work;
import edu.university.program.repository.WorkRepository;
import edu.university.program.service.WorkService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkServiceImpl implements WorkService {

    private WorkRepository workRepository;

    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public Work create(Work work) {
        try {
            return workRepository.save(work);
        } catch (IllegalArgumentException e) {
            throw new NullEntityReferenceException("Work cannot be 'null'");
        }
    }

    @Override
    public Work readById(long id) {
        Optional<Work> optional = workRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Work with id " + id + " not found");
    }

    @Override
    public Work update(Work work) {
        if (work != null) {
            Work oldWork = readById(work.getId());
            if (oldWork != null) {
                return workRepository.save(work);
            }
        }
        throw new NullEntityReferenceException("Work cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Work work = readById(id);
        if (work != null) {
            workRepository.delete(work);
        } else {
            throw new EntityNotFoundException("Work with id " + id + " not found");
        }
    }

    @Override
    public List<Work> getAll() {
        List<Work> works = workRepository.findAll();
        return works.isEmpty() ? new ArrayList<>() : works;
    }

    @Override
    public List<Work> getByWorkId(long workId) {
        List<Work> works = workRepository.getByWorkId(workId);
        return works.isEmpty() ? new ArrayList<>() : works;
    }
}
