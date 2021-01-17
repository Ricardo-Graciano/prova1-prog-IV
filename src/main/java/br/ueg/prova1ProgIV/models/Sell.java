package br.ueg.prova1ProgIV.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class Sell {
	
	@Id
	private String id;
	
	private int number;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date date = new Date();
	
	private Double vTotal;
	
	private User seller;
	private User customer;
	private List<SellProduct> sellProducts;
	
	public Sell() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Double getvTotal() {
		return vTotal;
	}
	public void setvTotal(Double vTotal) {
		this.vTotal = vTotal;
	}
	public List<SellProduct> getSellProducts() {
		return sellProducts;
	}
	public void setSellProducts(List<SellProduct> sellProducts) {
		this.sellProducts = sellProducts;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sell other = (Sell) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
