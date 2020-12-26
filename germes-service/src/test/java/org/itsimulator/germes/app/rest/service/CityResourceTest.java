package org.itsimulator.germes.app.rest.service;

import java.util.List;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.itsimulator.germes.app.rest.service.config.JerseyConfig;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link CityResourceTest} is integration test that verifies
 * {@link CityResource}
 * @author Morenets
 *
 */
public class CityResourceTest extends JerseyTest{
	@Override
    protected Application configure() {
        return new JerseyConfig();
    }
 
    @Test
    public void testFindCitiesSuccess() {
		List<?> cities = target("cities").request().get(List.class);
        System.out.println("#cities="+cities.toString());
		assertNotNull(cities);
		assertTrue(cities.contains("Odessa"));
		assertTrue(cities.contains("Kiyv"));
    }
} 
