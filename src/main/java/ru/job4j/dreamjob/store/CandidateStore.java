package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final AtomicInteger ids = new AtomicInteger(4);

    private CandidateStore() {
        candidates.put(1, new Candidate(1,"Junior Java Developer"
                , "Junior Java Candidate", LocalDateTime.now(), new City(1, "Moscow")));
        candidates.put(2, new Candidate(2,"Middle Java Developer"
                , "Middle Java Candidate", LocalDateTime.now(), new City(2, "New York")));
        candidates.put(3, new Candidate(3,"Senior Java Developer"
                , "Senior Java Candidate", LocalDateTime.now(), new City(3, "St.Petersburg")));
    }


    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(ids.incrementAndGet());
        candidate.setCreated(LocalDateTime.now());
        candidates.putIfAbsent(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        candidates.replace(candidate.getId(), candidate);
    }

}
