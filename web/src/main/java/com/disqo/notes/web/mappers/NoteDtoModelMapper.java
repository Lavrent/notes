package com.disqo.notes.web.mappers;

import com.disqo.notes.core.models.NoteModel;
import com.disqo.notes.web.dtos.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteDtoModelMapper {

    List<NoteModel> toNoteModels(List<NoteDto> noteDtos);

    List<NoteDto> toNoteDtos(List<NoteModel> noteEntities);

    @Mapping(source = "id", target = "uuid")
    NoteModel toNoteModel(NoteDto noteDto);

    @Mapping(source = "uuid", target = "id")
    NoteDto toNoteDto(NoteModel noteEntity);
}