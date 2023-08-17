package ru.skyeng.post_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skyeng.post_service.entity.TrackPoint;

public interface TrackPointRepository extends JpaRepository<TrackPoint, Long> {

}
