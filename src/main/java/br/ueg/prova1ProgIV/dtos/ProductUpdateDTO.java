package br.ueg.prova1ProgIV.dtos;

public class ProductUpdateDTO {

	private String name;
	private Double vUnCom;
	private String picture;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getvUnCom() {
		return vUnCom;
	}
	public void setvUnCom(Double vUnCom) {
		this.vUnCom = vUnCom;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
