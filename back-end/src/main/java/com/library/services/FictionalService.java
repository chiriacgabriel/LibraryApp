package com.library.services;

import com.library.dto.FictionalDto;
import com.library.mapper.FictionalMapper;
import com.library.repository.FictionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FictionalService {

    private FictionalMapper fictionalMapper;
    private FictionalRepository fictionalRepository;

    @Autowired
    public FictionalService(FictionalMapper fictionalMapper, FictionalRepository fictionalRepository) {
        this.fictionalMapper = fictionalMapper;
        this.fictionalRepository = fictionalRepository;
    }

    public List<FictionalDto> getAllFictionals() {
        return fictionalRepository.findAllByOrderByNameOfFictionalAsc()
                                  .stream()
                                  .map(fictional -> fictionalMapper.map(fictional))
                                  .collect(Collectors.toList());
    }
}
