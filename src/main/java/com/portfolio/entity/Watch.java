package com.portfolio.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Watch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name = "investor_id" ,referencedColumnName = "id")
	@JsonIgnoreProperties("watchs")
	private Investor investor;
	//cascade = CascadeType.PERSIST 聯集新增
	@ManyToMany(cascade = CascadeType.PERSIST ,fetch = FetchType.EAGER) // 抓到WATCH連同所有一起查詢 //預設lazy
	@JoinTable(name="watch_tstock",
			joinColumns = {
					@JoinColumn(name = "watch_id",nullable = false,updatable = false)
	},
			inverseJoinColumns = {
					@JoinColumn(name="tStock_id",nullable = false,updatable = false)
			}
	
	)
	//加addStock 給預設資料
	//private Set<TStock> tStock;
//	CopyOnWriteArraySet 複製出來再撰寫，比免產生多執行續
	private Set<TStock> tStocks=new CopyOnWriteArraySet<TStock>();

	public Set<TStock> gettStocks() {
		return tStocks;
	}

	public void settStocks(Set<TStock> tStocks) {
		this.tStocks = tStocks;
	}

	public Watch() {
	}

	public Watch(String name, Investor investor) {
		super();
		this.name = name;
		this.investor = investor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
//	--------------------------------------------
	public Set<TStock> addtStock(TStock tStock){
		tStocks.add(tStock);
		return tStocks;
		
		
	}
	public Set<TStock> removetStock(TStock tStock){
		tStocks.remove(tStock);
		return tStocks;
		
		
	}
	
}
