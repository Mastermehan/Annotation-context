package ru.netology.service;

import ru.netology.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.model.Post;
import org.springframework.stereotype.Service;
import ru.netology.repository.PostRepository;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.PostRepository;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository repository;

    public List<Post> all() {

        return repository.all();
    }

    public Post getById(long id) {

        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {

        return repository.save(post);
    }

    public void removeById(long id) {

        repository.removeById(id);
    }
}