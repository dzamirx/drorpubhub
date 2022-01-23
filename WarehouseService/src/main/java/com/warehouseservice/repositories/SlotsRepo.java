package com.warehouseservice.repositories;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.warehouseservice.Entity.Pallet;
import com.warehouseservice.Entity.Slot;

public interface SlotsRepo extends JpaRepository<Slot, Integer>
{
	
	//public List<Slot> findByHeightLessThanEqual(Double height);

	@Query(value = "SELECT * FROM slot WHERE height <= ?1 AND occupied = ?2 ", nativeQuery = true)
	List<Slot> findByHightLessThanEqual(Double height,Boolean b);
			
	@Query(value = "SELECT * FROM slot WHERE id = ?1", nativeQuery = true)
	Slot findSlot(Integer id);
	
	@Transactional
	void deleteById(Integer id);
	
	

}
