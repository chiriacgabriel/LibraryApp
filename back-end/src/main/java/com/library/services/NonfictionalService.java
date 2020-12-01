package com.library.services;

import com.library.dto.NonfictionalDto;
import com.library.mapper.NonfictionalMapper;
import com.library.repository.NonfictionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NonfictionalService {

    private NonfictionalRepository nonfictionalRepository;
    private NonfictionalMapper nonfictionalMapper;

    @Autowired
    public NonfictionalService(NonfictionalRepository nonfictionalRepository, NonfictionalMapper nonfictionalMapper) {
        this.nonfictionalRepository = nonfictionalRepository;
        this.nonfictionalMapper = nonfictionalMapper;
    }

    public List<NonfictionalDto> getAllNonfictional() {
        return nonfictionalRepository.findAllByOrderByNameOfNonfictionalAsc()
                .stream().map(nonfictional -> nonfictionalMapper.map(nonfictional))
                .collect(Collectors.toList());
    }
}
