package vo;

public class naverBlog {
	private String title; // 문서 제목
	private String link; // 문서 링크
	private String postdate; // 블로그 포스트 작성 날짜
	private String description; // 문서의 내용을 요약한 패시지 정보
	private String bloggername; // 포스트를 작성한 블로거의 이름
	private String bloggerlink; // 포스트를 작성한 블로거의 링크
	
	public naverBlog() {
	}

	public naverBlog(String title, String link, String postdate, String description, String bloggername,
			String bloggerlink) {
		this.title = title;
		this.link = link;
		this.postdate = postdate;
		this.description = description;
		this.bloggername = bloggername;
		this.bloggerlink = bloggerlink;
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

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBloggername() {
		return bloggername;
	}

	public void setBloggername(String bloggername) {
		this.bloggername = bloggername;
	}

	public String getBloggerlink() {
		return bloggerlink;
	}

	public void setBloggerlink(String bloggerlink) {
		this.bloggerlink = bloggerlink;
	}

	@Override
	public String toString() {
		return "naverBlog [title=" + title + ", link=" + link + ", postdate=" + postdate + ", description="
				+ description + ", bloggername=" + bloggername + ", bloggerlink=" + bloggerlink + "]";
	}
	
}