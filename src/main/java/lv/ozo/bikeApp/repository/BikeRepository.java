package lv.ozo.bikeApp.repository;

import lv.ozo.bikeApp.entity.Bike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository  extends CrudRepository <Bike, Long> {
    List<Bike> findByName(String name);

}
