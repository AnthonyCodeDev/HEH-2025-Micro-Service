package be.heh.reviewservice.mapper;

import be.heh.reviewservice.Modele.Review;
import be.heh.reviewservice.persistence.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "version", ignore = true),
        @Mapping(target = "serviceAddress", ignore = true)
    })
    ReviewEntity apiToEntity(Review api);

    @Mapping(target = "serviceAddress", ignore = true)
    Review entityToApi(ReviewEntity entity);

    List<Review> entityListToApiList(List<ReviewEntity> entity);
}
