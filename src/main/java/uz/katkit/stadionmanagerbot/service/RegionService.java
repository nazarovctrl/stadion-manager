package uz.katkit.stadionmanagerbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.katkit.stadionmanagerbot.entity.RegionEntity;
import uz.katkit.stadionmanagerbot.repository.RegionRepository;

import java.util.List;
import java.util.Stack;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public Stack<RegionEntity> getList() {
        return regionRepository.findByVisible(true);
    }
}
