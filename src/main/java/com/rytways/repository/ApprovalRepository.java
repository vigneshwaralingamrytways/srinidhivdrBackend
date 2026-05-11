package com.rytways.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rytways.model.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long>{



	@Query(value = "SELECT * FROM approval" +
            " WHERE unit_name = :machineName AND region_name = :regionName and price_revised_date= :priceRevisedDate",nativeQuery = true)
        
	List<Approval> findAllApprovers(@Param("machineName") String machineName, @Param("regionName") String region,@Param("priceRevisedDate")LocalDate priceRevisedDate);

	
	@Query(value = "SELECT * FROM approval" +
            " WHERE region_name = :regionName and price_revised_date = :priceRevisedDate" ,nativeQuery = true)
	List<Approval> findAllApproverExports(@Param("regionName") String regionName,@Param("priceRevisedDate")LocalDate priceRevisedDate);


	List<Approval> findByRegionNameAndPriceRevisedDate(String regionName, LocalDate revisedDate);


	 @Query("SELECT DISTINCT a.priceRevisedDate FROM Approval a WHERE a.regionName = :regionName AND a.unitName = :unitName")
	    List<LocalDate> findDistinctPriceRevisedDateByRegionNameAndUnitName(@Param("regionName") String regionName, @Param("unitName") String unitName);


	List<Approval> findByUnitNameAndPriceRevisedDate(String unitName, LocalDate priceRevisedDate);





}
