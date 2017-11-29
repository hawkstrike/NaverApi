package vo;

public class naverEncyc {
	private String title; // 문서 제목
	private String link; // 사전 정의 정보 및 추가 정보를 볼 수 있는 링크
	private String description; // 문서의 내용을 요약한 패시지 정보
	private String thumbnail; // 썸네일 이미지 링크
	
	public naverEncyc() {
	}

	public naverEncyc(String title, String link, String description, String thumbnail) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.thumbnail = thumbnail;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "naverEncyc [title=" + title + ", link=" + link + ", description=" + description + ", thumbnail="
				+ thumbnail + "]";
	}
	
}