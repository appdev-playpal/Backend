package org.example.websocketserver.repository;

import org.example.websocketserver.model.Hobby;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class InMemoryHobbyRepo {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final Set<Hobby> hobbyRepo = new HashSet<>();

    public void addHobby(Hobby hobby) {
        if (hobbyRepo.contains(hobby) || hobby == null) {
            return;
        }
        hobbyRepo.add(hobby);
    }

    public void removeHobby(Hobby hobby) {
        hobbyRepo.remove(hobby);
    }

    public Hobby findHobbyById(int id) {
        for (Hobby hobby : hobbyRepo) {
            if(hobby.getId() == id) {
                return hobby;
            }
        }
        return null;
    }

    public Set<Hobby> getAllHobbies() {
        return hobbyRepo;
    }
}
