package ge.ibsu.demo.service;

import ge.ibsu.demo.dto.*;
import ge.ibsu.demo.entity.City;
import ge.ibsu.demo.repository.CityRepository;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> retrieveAllCities() {
        return cityRepository.findAll();
    }

    public City getCity(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Transactional
    public City addCity(AddCityDTO addCity) throws Exception {

        City city = new City();
        GeneralUtil.getCopyOf(addCity, city);

        return cityRepository.save(city);
    }

    public Slice<City> search(SearchCity searchCity, Paging paging) {
        String searchText = null;
        if (searchCity.getSearchText() != null && !searchCity.getSearchText().equals("")) {
            searchText = "%" + searchCity.getSearchText() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage() - 1, paging.getSize(), Sort.by("city_id").ascending());
        return cityRepository.search(searchText, pageable);

    }
}