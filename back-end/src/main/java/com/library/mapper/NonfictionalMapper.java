package com.library.mapper;

import com.library.dto.NonfictionalDto;
import com.library.model.Nonfictional;
import org.springframework.stereotype.Service;

@Service
public class NonfictionalMapper {

    public Nonfictional map(NonfictionalDto nonfictionalDto){
        return Nonfictional.builder()
                .enumNonfictional(nonfictionalDto.getEnumNonfictional())
                .nameOfNonfictional(nonfictionalDto.getNameOfNonfictional())
                .bookListNonfictional(nonfictionalDto.getBookListNonfictional())
                .build();
    }

    public NonfictionalDto map(Nonfictional nonfictional){
        return NonfictionalDto.builder()
                .id(nonfictional.getId())
                .enumNonfictional(nonfictional.getEnumNonfictional())
                .nameOfNonfictional(nonfictional.getNameOfNonfictional())
                .bookListNonfictional(nonfictional.getBookListNonfictional())
                .build();
    }

}
