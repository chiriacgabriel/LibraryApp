package com.library.mapper;

import com.library.dto.FictionalDto;
import com.library.model.Fictional;
import org.springframework.stereotype.Service;

@Service
public class FictionalMapper {

    public Fictional map(FictionalDto fictionalDto){
        return Fictional.builder()
                .enumFictional(fictionalDto.getEnumFictional())
                .nameOfFictional(fictionalDto.getNameOfFictional())
                .bookListFictional(fictionalDto.getBookListFictional())
                .build();
    }

    public FictionalDto map(Fictional fictional){
        return FictionalDto.builder()
                .id(fictional.getId())
                .enumFictional(fictional.getEnumFictional())
                .nameOfFictional(fictional.getNameOfFictional())
                .bookListFictional(fictional.getBookListFictional())
                .build();
    }
}
