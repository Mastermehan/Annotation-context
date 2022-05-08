package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {

    private final Map<Long, Post> posts = new ConcurrentHashMap<>(){};
    private final AtomicLong counter = new AtomicLong(0L);

    public List<Post> all() {  //вызвать весь список обьектов

        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id)); //ofNullable если ему передать значение null в качестве параметра
    }

    public Post save(Post post) {  //пост сохраняем
        if (post.getId() != 0 && !posts.containsKey(post.getId())) {
            throw new NotFoundException();
        }

        if (post.getId() == 0) {
            var newId = counter.incrementAndGet();//Атомарный класс  AtomicLong его метод
            // incrementAndGet который увеличивает предыдущее значение на единицу
            // и возвращает значение после обновления, которое имеет тип данных int
            post.setId(newId); //добавляем
        }

        posts.put(post.getId(), post); //записываем id
        return post;
    }

    public void removeById(long id) { //удаление по id
        if (!posts.containsKey(id)) // ContainsKey метод для проверки наличия id
            throw new NotFoundException();
        posts.remove(id);// пост удалить
    }
}
