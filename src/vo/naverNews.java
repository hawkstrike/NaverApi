package vo;

public class naverNews {
	private String title; // 뉴스 제목
	private String originallink; // 뉴스를 제공한 언론사의 링크
	private String link; // 네이버 뉴스 문서 링크
	private String description; // 뉴스의 내용을 요약한 패시지 정보
	private String pubDate; // 문서가 네이버에 제공된 시간
	
	public naverNews() {
	}

	public naverNews(String title, String originallink, String link, String description, String pubDate) {
		this.title = title;
		this.originallink = originallink;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginallink() {
		return originallink;
	}

	public void setOriginallink(String originallink) {
		this.originallink = originallink;
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

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "naverNews [title=" + title + ", originallink=" + originallink + ", link=" + link + ", description="
				+ description + ", pubDate=" + pubDate + "]";
	}
	
}