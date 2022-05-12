package com.portfolio.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class TStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;//商品名稱
	@Column
	private String symbol;//商品代號
	
	//以下是報價資訊
	@Column
	private BigDecimal preClosed;//昨日收盤價
	
	@Column
	private BigDecimal price;
	
	@Column
	private BigDecimal changePrice;
	
	@Column
	private BigDecimal changeInPercent;
	
	@Column
	private Long volumn;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	@ManyToOne
	@JoinColumn(name="classify_id" ,referencedColumnName = "id")
	@JsonIgnoreProperties("tStocks")
	private Classify classify;
	 
	public TStock() {}

	public TStock(String symbol,String name,  Classify classify) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.classify = classify;
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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getPreClosed() {
		return preClosed;
	}

	public void setPreClosed(BigDecimal preClosed) {
		this.preClosed = preClosed;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getChangePrice() {
		return changePrice;
	}

	public void setChangePrice(BigDecimal changePrice) {
		this.changePrice = changePrice;
	}

	public BigDecimal getChangeInPercent() {
		return changeInPercent;
	}

	public void setChangeInPercent(BigDecimal changeInPercent) {
		this.changeInPercent = changeInPercent;
	}

	public Long getVolumn() {
		return volumn;
	}

	public void setVolumn(Long volumn) {
		this.volumn = volumn;
	}

	

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Classify getClassify() {
		return classify;
	}

	public void setClassify(Classify classify) {
		this.classify = classify;
	}
	
	
	
	
	

}
