package com.example.world.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.domain.Country;
import com.example.world.repository.WorldDao;
import com.example.world.repository.inmemory.InMemoryWorldDao;

@RestController
@RequestScope
public class WorldApi {
	
	private final WorldDao worldDao;
		
	@Autowired
	public WorldApi(WorldDao worldDao) {
		super();
		this.worldDao = worldDao;
	}


	@GetMapping("/continents")
	public Collection<String> getAllContinents(){
		return worldDao.getAllContinents();
	}

	@GetMapping("/countries")
	public Collection<Country> getAllContinents(@RequestParam(required = false, defaultValue = "Asia") String continent){
		return worldDao.findCountriesByContinent(continent);
	}

}
