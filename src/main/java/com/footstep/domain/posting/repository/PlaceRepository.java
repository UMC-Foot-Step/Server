package com.footstep.domain.posting.repository;

import com.footstep.domain.posting.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByLatitudeAndLongitude(Double latitude, Double longitude);
}
