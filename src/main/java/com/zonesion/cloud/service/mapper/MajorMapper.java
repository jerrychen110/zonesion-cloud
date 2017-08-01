package com.zonesion.cloud.service.mapper;

import com.zonesion.cloud.domain.*;
import com.zonesion.cloud.service.dto.MajorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Major and its DTO MajorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MajorMapper extends EntityMapper <MajorDTO, Major> {
    
    
    default Major fromId(Long id) {
        if (id == null) {
            return null;
        }
        Major major = new Major();
        major.setId(id);
        return major;
    }
}
