package vo;

public class naverMovie {
	private String title; // 영화 제목
	private String link; // 영화 링크
	private String image; // 썸네일 이미지의 링크
	private String subtitle; // 영화의 영문 제목
	private String pubDate; // 영화의 제작년도
	private String director; // 영화의 감독
	private int userRating; // 영화에 대한 유저들의 평점
	
	public naverMovie() {
	}

	public naverMovie(String title, String link, String image, String subtitle, String pubDate, String director,
			int userRating) {
		this.title = title;
		this.link = link;
		this.image = image;
		this.subtitle = subtitle;
		this.pubDate = pubDate;
		this.director = director;
		this.userRating = userRating;
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getUserRating() {
		return userRating;
	}

	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}
	
}