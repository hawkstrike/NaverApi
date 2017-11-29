package vo;

public class naverImage {
	private String title; // 이미지 제목
	private String link; // 이미지 링크
	private String thumbnail; // 이미지의 썸네일 링크
	private int sizewidth; // 이미지의 너비(단위 : pixel)
	private int sizeheight; // 이미지의 높이
	
	public naverImage() {
	}

	public naverImage(String title, String link, String thumbnail, int sizewidth, int sizeheight) {
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

	public int getSizewidth() {
		return sizewidth;
	}

	public void setSizewidth(int sizewidth) {
		this.sizewidth = sizewidth;
	}

	public int getSizeheight() {
		return sizeheight;
	}

	public void setSizeheight(int sizeheight) {
		this.sizeheight = sizeheight;
	}

	@Override
	public String toString() {
		return "naverImage [title=" + title + ", link=" + link + ", thumbnail=" + thumbnail + ", sizewidth=" + sizewidth
				+ ", sizeheight=" + sizeheight + "]";
	}
	
}