package org.itsimulator.germes.app.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jersey.repackaged.com.google.common.collect.Lists;

@Path("cities")
/**
 * {@link CityResource} is REST web-service that handles city-related requests  
 * @author Morenets
 *
 */
public class CityResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> findCities() {
		return Lists.newArrayList("Odessa", "Kiyv");
	}
}
