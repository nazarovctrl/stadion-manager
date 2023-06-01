package uz.katkit.stadionmanagerbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.katkit.stadionmanagerbot.entity.RegionEntity;


import java.util.Stack;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    Stack<RegionEntity> findByVisible(boolean visible);

}
