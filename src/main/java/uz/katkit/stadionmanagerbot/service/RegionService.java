package uz.katkit.stadionmanagerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import uz.katkit.stadionmanagerbot.entity.RegionEntity;
import uz.katkit.stadionmanagerbot.repository.RegionRepository;
import java.util.List;
import java.util.Stack;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public Stack<RegionEntity> getList(boolean visible) {
        return regionRepository.findByVisible(visible);
    }

    public List<RegionEntity> getList() {
        return regionRepository.findAll();
    }

    public void addRegion(String regionName) {
        RegionEntity entity = new RegionEntity();
        entity.setName(regionName);
        regionRepository.save(entity);
    }
}
