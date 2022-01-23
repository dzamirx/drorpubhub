package com.Neo4jWarehouseService.Repositories;


import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.Neo4jWarehouseService.Entity.NeoPallet;
import com.Neo4jWarehouseService.Entity.NeoSlot;
@Repository
public interface NeoRepo extends Neo4jRepository<NeoSlot, Long> {

	
	public List<NeoSlot> findAll();
	
	@Query("MATCH (n:NeoSlot) RETURN n LIMIT 25")
	List<NeoSlot> findAllslots();
	
	
}
