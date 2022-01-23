package com.warehouseservice.repositories;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.warehouseservice.Entity.Pallet;
import com.warehouseservice.Entity.Slot;


public interface PalletsRepo extends JpaRepository<Pallet, Long>
{
	
	public List<Pallet> findByBatch (Integer batch);

	@Transactional
	void deleteById(Long id);

//	@Transactional
//	public List<Pallet> findAllpalletsbyheight();
	
	@Query(value = "SELECT * FROM pallet WHERE height <= ?1 ", nativeQuery = true)
	List<Pallet> findByHightLessThanEqual(Double height);
	
	//public List<Pallet> findbyProduct(String product);

	//public List<Pallet> findbyBatch(long id);

}


