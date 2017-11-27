package vo;

public class naverImage {
	private String title; // 이미지 제목
	private String link; // 이미지 링크
	private String thumbnail; // 이미지의 썸네일 링크
	private String sizewidth; // 이미지의 너비(단위 : pixel)
	private String sizeheight; // 이미지의 높이
	
	public naverImage() {
	}

	public naverImage(String title, String link, String thumbnail, String sizewidth, String sizeheight) {
		this.title = title;
		this.link = link;
		this.thumbnail = thumbnail;
		this.sizewidth = sizewidth;
		this.sizeheight = sizeheight;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSizewidth() {
		return sizewidth;
	}

	public void setSizewidth(String sizewidth) {
		this.sizewidth = sizewidth;
	}

	public String getSizeheight() {
		return sizeheight;
	}

	public void setSizeheight(String sizeheight) {
		this.sizeheight = sizeheight;
	}
	
}