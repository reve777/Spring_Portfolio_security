package com.portfolio.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.entity.Investor;
import com.portfolio.entity.Watch;
import com.portfolio.repository.InvestorRepository;
import com.portfolio.repository.WatchRepository;

@RestController
@RequestMapping("/investor")
public class InvestorController {
	@Autowired
	private InvestorRepository investorRepository;
	@Autowired
	private WatchRepository watchRepository;
	
	@GetMapping(value = {"/","/query"})
	public List<Investor>query(){
		return investorRepository.findAll();
		
	}
	
	@GetMapping(value = {"/{id}","/get/{id}"})
	public Investor get(@PathVariable("id")Integer id) {
		Optional<Investor>optInvestor =investorRepository.findById(id);
		return optInvestor.isPresent()?optInvestor.get():null;
		
	}
	@PostMapping(value = {"/","/add"})
	@Transactional
	public Investor add(@RequestBody Map<String ,String>map) {
		Investor investor =new Investor();
		investor.setUsername(map.get("username"));
		investor.setEmail(map.get("email"));
		investor.setBalance(Integer.parseInt(map.get("balance")));
		//存檔
		investorRepository.save(investor);
		//存檔 Watch
		String watchName=investor.getUsername()+"的投資組合";
		Watch watch =new Watch();
		watch.setName(watchName);
		watch.setInvestor(investor);//建立關聯
		watchRepository.save(watch);
		return investor;
	}
	
	//
	@PutMapping(value= "/{id}")
	@Transactional
	public Investor update(@PathVariable("id")Integer id,@RequestBody Map<String ,String>map) {
		Investor investor= get(id);
		if(investor==null) {
			return null;
		}
		investor.setUsername(map.get("username"));
		investor.setEmail(map.get("email"));
		investor.setBalance(Integer.parseInt(map.get("balance")));
		
		investor=investorRepository.saveAndFlush(investor);
		return investor;
	}
	
	@DeleteMapping(value = {"/{id}","/delete/{id}"})
	@Transactional
	public Boolean delete(@PathVariable("id")Integer id) {
		Investor investor=get (id);
		if(investor==null) {
			return false;
			
		}
		investorRepository.deleteById(id);
		return true;
	}
	
	
}
