package com.portfolio.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.entity.TStock;
import com.portfolio.entity.Watch;
import com.portfolio.repository.TStockRepository;
import com.portfolio.repository.WatchRepository;

@RestController
@RequestMapping("/watch")
public class WatchController {
	
	@Autowired
	private WatchRepository watchRepository;
	
	@Autowired
	private TStockRepository tStockRepository;
	
	@GetMapping(value = {"/{id}","/get/{id}"})
	@Transactional
	public Watch get(@PathVariable("id")Integer id) {
		Watch watch =watchRepository.findById(id).get();
		//對應 	@ManyToMany(fetch = FetchType.EAGER) 
//		watch.gettStock().size();//因為預設@manytomany 預設資料載入是Lazy,所以加入此行可取得tStocks 資料
		return watch;
		
	}
	@GetMapping(value = {"/","/quert"})
	@Transactional
	public List<Watch>query(){
		List<Watch>list =watchRepository.findAll();
		return list;
	}
	@GetMapping(value = {"/{id}/add/{tstock_id}"})
	@Transactional
	public Watch add_tstock(@PathVariable("id")Integer id,@PathVariable("tstock_id")Integer tstock_id) {
		Watch watch =watchRepository.findById(id).get();
		TStock ts=tStockRepository.findById(tstock_id).get();
		watch.addtStock(ts);
		watchRepository.saveAndFlush(watch);
		return get(id);
	}
	@PutMapping(value = {"/{id}","/update/{id}"})
	@Transactional
	public Watch update(@PathVariable("id")Integer id, @RequestBody Map<String,String>map) {
		Optional<Watch> optWatch =watchRepository.findById(id);
		if(optWatch.isPresent()) {
			return null;
		}
		Watch watch =optWatch.get();
		watch.setName(map.get("name"));
		return watchRepository.saveAndFlush(watch);
		
	}
	@DeleteMapping(value = {"/{id}/remove/{tstock_id}"})
	@Transactional
	public Watch remove_tstock(@PathVariable("id")Integer id ,@PathVariable("tstock_id")Integer tstock_id) {
		Watch watch =watchRepository.findById(id).get();
		TStock ts=tStockRepository.findById(tstock_id).get();
		watch.removetStock(ts);
		watchRepository.saveAndFlush(watch);
		
		return get(id);
	}
	
	
	
}
