package vo;

public class naverWebkr {
	private String title; // 문서의 제목
	private String link; // 문서의 링크
	private String descrption; // 문서의 내용을 요약한 패시지 정보
	
	public naverWebkr() {
	}

	public naverWebkr(String title, String link, String descrption) {
		this.title = title;
		this.link = link;
		this.descrption = descrption;
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

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	
}