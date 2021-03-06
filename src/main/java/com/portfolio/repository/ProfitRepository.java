package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.Profit;

@Repository(value ="profitRepository")
public interface ProfitRepository extends JpaRepository<Profit, Integer>{
	@Query(value = "SELECT a FROM Profit a WHERE a.invid=?1")
	public List<Profit> findByInvid(@Param("invid")Integer invid);
}
