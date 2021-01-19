package br.ueg.prova1ProgIV.models;

public class SellProduct extends Product {
	
	private Double qCom;
	private Double vDesc = 0.0;
	private Double vProd;
	
	public SellProduct() {}
	
	public SellProduct( String id, String name, String description, Double qCom) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.qCom = qCom;
	}

	public Double getqCom() {
		return qCom;
	}

	public void setqCom(Double qCom) {
		this.qCom = qCom;
	}

	public Double getvDesc() {
		return vDesc;
	}

	public void setvDesc(Double vDesc) {
		this.vDesc = vDesc;
	}

	public Double getvProd() {
		return vProd;
	}

	public void setvProd(Double vProd) {
		this.vProd = vProd;
	}
}
