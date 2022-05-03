package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;
@Service
public class CandidateService {

    private final CandidateStore store;

    public CandidateService(CandidateStore store) {
        this.store = store;
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void addCandidate(Candidate candidate) {
        store.add(candidate);
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }
}