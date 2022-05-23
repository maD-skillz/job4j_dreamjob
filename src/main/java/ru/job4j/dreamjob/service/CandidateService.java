package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.CandidateDbStore;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;
import java.util.List;

@Service
public class CandidateService {

    private final CandidateDbStore candidateDbStore;

    private final CityService cityService;

    public CandidateService(CandidateDbStore candidateDbStore, CityService cityService) {
        this.candidateDbStore = candidateDbStore;
        this.cityService = cityService;
    }

    public Candidate findById(int id) {
        return candidateDbStore.findById(id);
    }

    public void addCandidate(Candidate candidate) {
        candidateDbStore.add(candidate);
    }

    public Collection<Candidate> findAll() {
        return candidateDbStore.findAll();
    }

    public List<Candidate> findAllCities() {
        List<Candidate> candidates = (List<Candidate>) candidateDbStore.findAll();
        candidates.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return candidates;
    }

    public void update(Candidate candidate) {
        candidateDbStore.update(candidate);
    }
}
