package vo;

public class naverBook {
	private String title; // 문서 제목
	private String link; // 문서 링크
	private String image; // 썸네일 이미지 링크
	private String author; // 저자 정보
	private int price; // 정가 정보
	private int discount; // 할인 가격 정보
	private String publisher; // 출판사 정보
	private String isbn; // isbn 숫자
	private String description; // 문서의 내용을 요약한 패시지 정보
	private String pubdate; // 출간일 정보
	
	public naverBook() {
	}

	public naverBook(String title, String link, String image, String author, int price, int discount, String publisher,
			String isbn, String description, String pubdate) {
		this.title = title;
		this.link = link;
		this.image = image;
		this.author = author;
		this.price = price;
		this.discount = discount;
		this.publisher = publisher;
		this.isbn = isbn;
		this.description = description;
		this.pubdate = pubdate;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	
}