package vo;

public class naverShop {
	private String title; // 제목
	private String link; // 문서의 링크
	private String image; // 썸네일 이미지의 링크
	private int lprice; // 최저가 정보 없으면 0으로 표시
	private int hprice; // 최고가 정보 없으면 0으로 표시
	private String mallName; // 상품을 판매하는 쇼핑몰의 상호
	private int productId; // 해당 상품에 대한 id
	/*
	 * 일반상품
	 * 1 - 가격비교 상품
	 * 2 - 가격비교 비매칭 일반상품
	 * 3 - 가격비교 매칭 일반상품
	 * 
	 * 중고상품
	 * 4 - 가격비교 상품
	 * 5 - 가격비교 비매칭 일반상품
	 * 6 - 가격비교 매칭 일반상품
	 * 
	 * 단종상품
	 * 7 - 가격비교 상품
	 * 8 - 가격비교 비매칭 일반상품
	 * 9 - 가격비교 매칭 일반상품
	 * 
	 * 판매예정상품
	 * 10 - 가격비교 상품
	 * 11 - 가격비교 비매칭 일반상품
	 * 12 - 가격비교 매칭 일반상품
	 */
	private int productType;
	
	public naverShop() {
	}

	public naverShop(String title, String link, String image, int lprice, int hprice, String mallName, int productId, int productType) {
		this.title = title;
		this.link = link;
		this.image = image;
		this.lprice = lprice;
		this.hprice = hprice;
		this.mallName = mallName;
		this.productId = productId;
		this.productType = productType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLprice() {
		return lprice;
	}

	public void setLprice(int lprice) {
		this.lprice = lprice;
	}

	public int getHprice() {
		return hprice;
	}

	public void setHprice(int hprice) {
		this.hprice = hprice;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "naverShop [title=" + title + ", link=" + link + ", image=" + image + ", lprice=" + lprice + ", hprice="
				+ hprice + ", mallName=" + mallName + ", productId=" + productId + ", productType=" + productType + "]";
	}
	
}