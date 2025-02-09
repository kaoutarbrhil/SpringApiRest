package com.api.springapirest.mapper;


public interface IMapper<CreateDTO, UpdateDTO, ResponseDTO, Entity, ID> {

    public ResponseDTO toDTO(final Entity entity);

    public Entity createReqtoEntity(final CreateDTO createDTO);

    public Entity updateReqtoEntity(final ID id, final UpdateDTO updateDTO);
}
