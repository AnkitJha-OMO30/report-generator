package com.saison.reportgenerator.util;

import com.saison.reportgenerator.model.samples.DBField1;
import com.saison.reportgenerator.model.samples.RequestPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PojoMapper {

    @Mapping(target = "dbFieldStr", source = "field1")
    @Mapping(target = "dbField22", source = "field2.field22")
    @Mapping(target = "dbFieldArray", source = "field3")
    DBField1 toDBField1Object(RequestPojo pojo);

}
