package vo;

public class naverLocal {
	private String title; // 업체, 기관명
	private String link; // 업체, 기관의 상세 정보가 제공되는 링크
	private String description; // 업체, 기관명에 대한 설명
	private String telephone; // 업체, 기관명의 전화번호
	private String address; // 업체, 기관명의 주소
	private String roadAddress; // 업체, 기관명의 도로명 주소
	private int mapx; // 업체, 기관명의 x좌표
	private int mapy; // 업체, 기관명의 y좌표
	
	public naverLocal() {
	}

	public naverLocal(String title, String link, String description, String telephone, String address,
			String roadAddress, int mapx, int mapy) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.telephone = telephone;
		this.address = address;
		this.roadAddress = roadAddress;
		this.mapx = mapx;
		this.mapy = mapy;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public int getMapx() {
		return mapx;
	}

	public void setMapx(int mapx) {
		this.mapx = mapx;
	}

	public int getMapy() {
		return mapy;
	}

	public void setMapy(int mapy) {
		this.mapy = mapy;
	}
	
}