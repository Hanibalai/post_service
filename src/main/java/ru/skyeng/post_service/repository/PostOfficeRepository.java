package ru.skyeng.post_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.post_service.entity.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {

}
