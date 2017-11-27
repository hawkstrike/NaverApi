package naverConnection;

import java.net.HttpURLConnection;
import java.net.URL;

public class naverConnection {
	private URL url;
	private HttpURLConnection conn;
	// 발급받은 clientId
	private String clientId;
	// 발급받은 clientSecret
	private String clientSecret;
	// Api에 접근할 때 공통적으로 등러가는 주소를 위한 변수
	private String host = "https://openapi.naver.com/v1/search/";
	// 접속 방식을 설정하는 변수
	private String method = "GET";
	// xml, json 둘 중 반환 방식을 결정하는 변수
	private String format;
	/* blog, news, book, encyc, movie, cafearticle, kin,
	 * local, webkr, image, shop 중 어떤걸 검색할지 고르는 변수
	 */
	private String request;
	// 실제로 Api에 접근할 주소를 위한 변수
	private String address;
	// 검색할 쿼리를 지정하는 변수 - 필수
	private String query;
	
	public naverConnection() {
	}
	
	public int getResponseCode() {
		int getResponseCode = 0;
		try {
			url = new URL(address);
			conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod(method);
			conn.setRequestProperty("clientId", clientId);
			conn.setRequestProperty("clientSecret", clientSecret);
			conn.connect();
			
			getResponseCode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getResponseCode;
	}
}