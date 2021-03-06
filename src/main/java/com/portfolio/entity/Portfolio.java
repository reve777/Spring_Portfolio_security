package com.portfolio.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Portfolio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Double cost;//買進成本
	@Column
	private Integer amount;//買進股數
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();//交易時間
	
	@ManyToOne
	@JoinColumn(name = "investor_id", referencedColumnName = "id")
	
	@JsonIgnoreProperties("portfolios")
	private Investor investor;//投資人
	
	@OneToOne
	@JoinColumn(name = "tStock_id", foreignKey = @ForeignKey(name = "tStock_fk", value = ConstraintMode.CONSTRAINT))
	private TStock tStock;//對應股票ID

	public Portfolio() {
	}

	public Portfolio(Double cost, Integer amount, Investor investor, TStock tStock) {
		super();
		this.cost = cost;
		this.amount = amount;
		this.investor = investor;
		this.tStock = tStock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public TStock gettStock() {
		return tStock;
	}

	public void settStock(TStock tStock) {
		this.tStock = tStock;
	}
	

}
