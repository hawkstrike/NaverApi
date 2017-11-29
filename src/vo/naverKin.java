package vo;

public class naverKin {
	private String title; // 문서 제목
	private String link; // 문서 링크
	private String description; // 문서의 내용을 요약한 패시지 정보
	
	public naverKin() {
	}

	public naverKin(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
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

	@Override
	public String toString() {
		return "naverKin [title=" + title + ", link=" + link + ", description=" + description + "]";
	}
	
}