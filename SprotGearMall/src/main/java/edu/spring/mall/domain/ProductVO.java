package edu.spring.mall.domain;

public class ProductVO {
	private int productId;
	private String productName;
	private int productprice;
	private int productStock;
	private String productMaker;
	private String productImgPath;
	private String productCategory;
	private int productIsDeleted;
	// �׽�Ʈ�ϱ�
	public ProductVO(int productId, String productName, int productprice, int productStock, String productMaker,
			String productImgPath, String productCategory, int productIsDeleted) {
		this.productId = productId;
		this.productName = productName;
		this.productprice = productprice;
		this.productStock = productStock;
		this.productMaker = productMaker;
		this.productImgPath = productImgPath;
		this.productCategory = productCategory;
		this.productIsDeleted = productIsDeleted;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	public String getProductMaker() {
		return productMaker;
	}
	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}
	public String getProductImgPath() {
		return productImgPath;
	}
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductIsDeleted() {
		return productIsDeleted;
	}
	public void setProductIsDeleted(int productIsDeleted) {
		this.productIsDeleted = productIsDeleted;
	}
//staging test
	public ProductVO() {
		super();
	}
	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", productprice=" + productprice
				+ ", productStock=" + productStock + ", productMaker=" + productMaker + ", productImgPath="
				+ productImgPath + ", productCategory=" + productCategory + ", productIsDeleted=" + productIsDeleted
				+ "]";
	}
	
	
}
