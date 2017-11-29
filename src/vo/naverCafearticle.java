package vo;

public class naverCafearticle {
	private String title; // 문서 제목
	private String link; // 문서 링크
	private String description; // 문서의 내용을 요약한 패시지 정보
	private String cafename; // 문서가 작성된 카페 이름
	private String cafeurl; // 문서가 작성된 카페 링크
	
	public naverCafearticle() {
	}

	public naverCafearticle(String title, String link, String description, String cafename, String cafeurl) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.cafename = cafename;
		this.cafeurl = cafeurl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCafename() {
		return cafename;
	}

	public void setCafename(String cafename) {
		this.cafename = cafename;
	}

	public String getCafeurl() {
		return cafeurl;
	}

	public void setCafeurl(String cafeurl) {
		this.cafeurl = cafeurl;
	}

	@Override
	public String toString() {
		return "naverCafearticle [title=" + title + ", link=" + link + ", description=" + description + ", cafename="
				+ cafename + ", cafeurl=" + cafeurl + "]";
	}
	
}