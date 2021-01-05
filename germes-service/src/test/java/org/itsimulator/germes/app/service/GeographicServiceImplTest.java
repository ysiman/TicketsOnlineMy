package org.itsimulator.germes.app.service;

import java.util.List;
import java.util.Optional;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.entity.transport.TransportType;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.itsimulator.germes.app.persistence.repository.StationRepository;
import org.itsimulator.germes.app.persistence.repository.hibernate.HibernateCityRepository;
import org.itsimulator.germes.app.persistence.repository.hibernate.HibernateStationRepository;
import org.itsimulator.germes.app.persistence.repository.inmemory.InMemoryCityRepository;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contain unit-tests for {@link GeographicServiceImpl} 
 * @author Morenets
 *
 */
public class GeographicServiceImplTest {
	private GeographicService service;
	private static final int DEFAULT_CITY_ID = 1;

	/*@Before
	public void setup() {
		service = new GeographicServiceImpl(new InMemoryCityRepository());
	}
	*/

	@Before
	public void setup() {

		//service = new GeographicServiceImpl(new HibernateCityRepository(new SessionFactoryBuilder()));
		SessionFactoryBuilder builder = new SessionFactoryBuilder();
		CityRepository repository = new HibernateCityRepository(builder);
		StationRepository stationRepository = new HibernateStationRepository(builder);
		service = new GeographicServiceImpl(repository, stationRepository);
	}
	/*
	@Test
	public void testNoDataReturnedAtStart() {
		List<City> cities = service.findCities();
		assertTrue(cities.isEmpty());
	}
	
	@Test
	public void testSaveNewCitySuccess() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		service.saveCity(city);
		
		List<City> cities = service.findCities();
		assertEquals(cities.size(), 1);
		assertEquals(cities.get(0).getName(), "Odessa");
	}


	@Test
	public void testFindCityByIdSuccess() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		service.saveCity(city);

		Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
		assertTrue(foundCity.isPresent());
		assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
	}

	@Test
	public void testFindCityByIdNotFound() {
		Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
		assertFalse(foundCity.isPresent());
	}

	@Test
	public void testSearchStationsByNameSuccess() {
		City city = new City("Odessa");
		city.setId(DEFAULT_CITY_ID);
		city.addStation(TransportType.AUTO);
		city.addStation(TransportType.RAILWAY);
		service.saveCity(city);

		List<Station> stations = service.searchStations(StationCriteria.byName("Odessa"), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertEquals(stations.size(), 2);
		assertEquals(stations.get(0).getCity(), city);
	}

	@Test
	public void testSearchStationsByNameNotFound() {
		List<Station> stations = service.searchStations(StationCriteria.byName("Odessa"), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertTrue(stations.isEmpty());
	}

	@Test
	public void testSearchStationsByTransportTypeSuccess() {
		City city = new City("Odessa");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Kiev");
		city2.setId(2);
		city2.addStation(TransportType.AUTO);
		service.saveCity(city2);

		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertEquals(stations.size(), 2);
	}

	@Test
	public void testSearchStationsByTransportTypeNotFound() {
		City city = new City("Odessa");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Kiev");
		city2.setId(2);
		city2.addStation(TransportType.RAILWAY);
		service.saveCity(city2);

		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AVIA), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertTrue(stations.isEmpty());
	}


	@Test
	public void testMy() {
		City city = new City("Odessa");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Kiev");
		city2.setId(2);
		city2.addStation(TransportType.AUTO);
		service.saveCity(city2);

		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO),null);
		assertNotNull(stations);
		assertEquals(stations.size(), 2);
	}
	*/
	@Test
	public void t1() {
		List<City> cities = service.findCities();
		assertTrue(cities.isEmpty());
	}

	@Test
	public void t2() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		service.saveCity(city);

		List<City> cities = service.findCities();
		assertEquals(cities.size(), 1);
		assertEquals(cities.get(0).getName(), "Odessa");
	}


	@Test
	public void t3() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		service.saveCity(city);

		Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
		assertTrue(foundCity.isPresent());
		assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
	}

	@Test
	public void t4() {
		Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
		assertTrue(foundCity.isPresent());
	}

	@Test
	public void t5() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		city.setId(DEFAULT_CITY_ID);
		city.addStation(TransportType.AUTO);
		city.addStation(TransportType.RAILWAY);
		service.saveCity(city);

		List<Station> stations = service.searchStations(StationCriteria.byName("Odessa"), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertEquals(stations.size(), 2);
		assertEquals(stations.get(0).getCity(), city);
	}

	@Test
	public void t6() {
		List<Station> stations = service.searchStations(StationCriteria.byName("Odessa"), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertFalse(stations.isEmpty());
	}

	@Test
	public void t7() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Kiev");
		city.setDistrict("KievDistrict");
		city.setRegion("Ukrane");
		city2.setId(2);
		city2.addStation(TransportType.AUTO);
		service.saveCity(city2);

		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertEquals(stations.size(), 3);
	}

	@Test
	public void t8(){
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Kiev");
		city.setDistrict("KievDistrict");
		city.setRegion("Ukrane");
		city2.setId(2);
		city2.addStation(TransportType.RAILWAY);
		service.saveCity(city2);

		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AVIA), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertTrue(stations.isEmpty());
	}


	@Test
	public void t9() {
		City city = new City("Odessa");
		city.setDistrict("OdessaDistrict");
		city.setRegion("Ukrane");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Kiev");
		city.setDistrict("KievDistrict");
		city.setRegion("Ukrane");
		city2.setId(2);
		city2.addStation(TransportType.AUTO);
		service.saveCity(city2);

		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO),null);
		assertNotNull(stations);
		assertEquals(stations.size(), 6);
	}
}
