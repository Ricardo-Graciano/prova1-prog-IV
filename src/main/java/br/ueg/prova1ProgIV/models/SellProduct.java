package br.ueg.prova1ProgIV.models;

public class SellProduct extends Product {
	
	private Double qCom;
	private Double vDesc = 0.0;
	private Double vProd;
	
	public SellProduct() {

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
