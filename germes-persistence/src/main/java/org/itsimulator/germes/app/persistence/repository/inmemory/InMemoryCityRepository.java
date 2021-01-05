package org.itsimulator.germes.app.persistence.repository.inmemory;

import java.util.ArrayList;
import java.util.List;

import org.itsimulator.germes.app.infra.util.CommonUtil;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.persistence.repository.CityRepository;

/**
 * In-memory implementation of the {@link CityRepository} that stores
 * data in the RAM
 * @author Morenets
 *
 */
public class InMemoryCityRepository implements CityRepository {
	/**
	 * Internal list of cities
	 */
	private final List<City> cities;
	
	/**
	 * Auto-increment counter for entity id generation
	 */
	private int counter = 0;

	private int stationCounter = 0;

	public InMemoryCityRepository() {
		cities = new ArrayList<City>();
	}	

	@Override
	public void delete(final int cityId) {
	}

	@Override
	public List<City> findAll() {
		return CommonUtil.getSafeList(cities);
	}
	
	@Override
	public void save(final City city) {
		System.out.println("#saveInMemory");
		if (!cities.contains(city)) {
			city.setId(++counter);
			cities.add(city);
		}
		city.getStations().forEach((station) -> {
			if (station.getId() == 0) {
				station.setId(++stationCounter);
			}
		});
	}

	@Override
	public City findById(final int id) {
		System.out.println("#findByIdInMemory");
		return cities.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
	}	

}
