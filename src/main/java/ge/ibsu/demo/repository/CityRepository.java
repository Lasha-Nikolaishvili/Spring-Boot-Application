package ge.ibsu.demo.repository;

import ge.ibsu.demo.entity.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("(From City where :searchText is null or concat(cityId, concat(' ' , city)) like :searchText)")
    Slice<City> search(@Param("searchText") String searchText, Pageable pageable);
}
