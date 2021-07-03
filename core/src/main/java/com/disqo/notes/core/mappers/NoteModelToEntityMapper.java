package com.disqo.notes.core.mappers;

import com.disqo.notes.core.models.NoteModel;
import com.disqo.notes.repository.entities.NoteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteModelToEntityMapper {

    List<NoteEntity> toNoteEntities(List<NoteModel> noteModels);

    List<NoteModel> toNoteModels(List<NoteEntity> noteEntities);

    NoteEntity toNoteEntity(NoteModel noteModel);

    NoteModel toNoteModel(NoteEntity noteEntity);
}