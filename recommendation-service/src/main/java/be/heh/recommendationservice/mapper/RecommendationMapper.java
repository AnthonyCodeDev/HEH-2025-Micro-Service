package be.heh.recommendationservice.mapper;

import be.heh.recommendationservice.model.Recommendation;
import be.heh.recommendationservice.persistence.RecommendationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "version", ignore = true)
    })
    RecommendationEntity apiToEntity(Recommendation api);

    Recommendation entityToApi(RecommendationEntity entity);

    List<Recommendation> entityListToApiList(List<RecommendationEntity> entity);
}
