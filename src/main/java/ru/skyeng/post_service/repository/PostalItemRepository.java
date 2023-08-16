package ru.skyeng.post_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.post_service.entity.PostalItem;

@Repository
public interface PostalItemRepository extends JpaRepository<PostalItem, Long> {

}
