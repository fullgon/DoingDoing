package xyz.parkh.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.parkh.campus.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {
}
