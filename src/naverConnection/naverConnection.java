package naverConnection;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vo.naverBlog;
import vo.naverBook;
import vo.naverCafearticle;
import vo.naverEncyc;
import vo.naverImage;
import vo.naverKin;
import vo.naverLocal;
import vo.naverMovie;
import vo.naverNews;
import vo.naverShop;
import vo.naverWebkr;

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
	// blog, news, book, encyc, movie, cafearticle, kin, local, webkr, image, shop
	private String display; // 10(기본값), 100(최대)
	// blog, news, book, encyc, movie, cafearticle, kin, local, webkr, image, shop
	private String start; // 1(기본값), 1000(최대)
	// blog, news, book, cafearticle, kin, local, image
	/*
	 * kin - sim(유사도순 - 기본값), date(날짜순), point(평점순)
	 * local - random(유사도순 - 기본값), comment(카페/블로그 리뷰 순)
	 * shop - sim(기본값), date, asc(가격오름차순), dsc(가격내림차순)
	 */
	private String sort; // sim(기본값), date
	// image
	private String filter; // all, large, medium, small
	
	public naverConnection() {
	}
	
	/*
	 * 나머지 요청변수들을 기본값으로 사용하는 생성자 메소드
	 */
	public naverConnection(String clientId, String clientSecret, String format, String request, String query) throws UnsupportedEncodingException {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.format = format;
		this.request = request;
		this.address = this.host + this.request;
		this.query = URLEncoder.encode(query, "UTF-8");
		
		if(!"".equals(this.format))
			this.address += "." + this.format;
		if(!"".equals(this.query))
			this.address += "?query=" + this.query;
	}
	/*
	 * 공통 요청 변수를 사용하는 생성자 메소드
	 */
	public naverConnection(String clientId, String clientSecret, String format, String request, String query, String display, String start) throws UnsupportedEncodingException {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.format = format;
		this.request = request;
		this.address = this.host + this.request;
		this.query = URLEncoder.encode(query, "UTF-8");
		this.display = display;
		this.start = start;
		
		if(!"".equals(this.format))
			this.address += "." + this.format;
		if(!"".equals(this.query))
			this.address += "?query=" + this.query;
		if(!"".equals(this.display))
			this.address += "&display=" + this.display;
		if(!"".equals(this.start))
			this.address += "&start=" + this.start;
	}
	/*
	 * 접속을 시도하는 메소드
	 * 접속에 성공여부에 상관없이 naver로부터 받은 값을 리턴하며
	 * 200이면 접속에 성공한 것임.
	 */
	public int getResponseCode() {
		int getResponseCode = 0;
		try {
			url = new URL(address);
			conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod(method);
			conn.setRequestProperty("X-Naver-Client-Id", clientId);
			conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			conn.connect();
			
			getResponseCode = conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getResponseCode;
	}
	/*
	 * getResponseCode()를 통해 200을 반환 받았다면
	 * 정상접속이므로 이 메소드를 통해 결과 값들을 받아올 수 있고
	 * Format 형식에 따라 다른 메소드가 실행됨.
	 */
	public String getResponseData() {
		StringBuffer sb = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String inputLine;
			sb = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			
			/*if("xml".equals(getFormat()))
				getXmlData(sb.toString());
			if("json".equals(getFormat()))
				getJsonData(sb.toString());*/
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/*
	 * Format 형식이 xml이라면 xml형식으로 파싱하기 위해 준비하는 메소드
	 */
	public Element getXmlData(String inputLine) {
		Element element = null;
		
		if("xml".equals(getFormat())) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				InputStream is = new ByteArrayInputStream(inputLine.getBytes("UTF-8"));
				Document doc = builder.parse(is);
				element = doc.getDocumentElement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return element;
	}
	/*
	 * Format 형식이 json이라면 json형식으로 파싱하기 위해 준비하는 메소드
	 */
	public JSONObject getJsonData(String inputLine) {
		JSONObject json = null;
		
		if("json".equals(getFormat())) {
			json = new JSONObject(inputLine);
		}
		
		return json;
	}
	
	/*
	 * 접속을 해제하는 메소드
	 */
	public void disConnection() {
		conn.disconnect();
	}
	
	public Object getSearch(String inputLine) {
		Object obj = null;
		Element element = getXmlData(inputLine);
		JSONObject json = getJsonData(inputLine);
		
		if("xml".equals(getFormat())) {
			if("blog".equals(getRequest()))
				obj = blogSearchXml(element);
			if("book".equals(getRequest()))
				obj = bookSearchXml(element);
			if("cafearticle".equals(getRequest()))
				obj = cafeSearchXml(element);
			if("encyc".equals(getRequest()))
				obj = encycSearchXml(element);
			if("image".equals(getRequest()))
				obj = imageSearchXml(element);
			if("kin".equals(getRequest()))
				obj = kinSearchXml(element);
			if("local".equals(getRequest()))
				obj = localSearchXml(element);
			if("movie".equals(getRequest()))
				obj = movieSearchXml(element);
			if("news".equals(getRequest()))
				obj = newsSearchXml(element);
			if("shop".equals(getRequest()))
				obj = shopSearchXml(element);
			if("webkr".equals(getRequest()))
				obj = webSearchXml(element);
		}
		if("json".equals(getFormat())) {
			if("blog".equals(getRequest()))
				obj = blogSearchJson(json);
			if("book".equals(getRequest()))
				obj = bookSearchJson(json);
			if("cafearticle".equals(getRequest()))
				obj = cafeSearchJson(json);
			if("encyc".equals(getRequest()))
				obj = encycSearchJson(json);
			if("image".equals(getRequest()))
				obj = imageSearchJson(json);
			if("kin".equals(getRequest()))
				obj = kinSearchJson(json);
			if("local".equals(getRequest()))
				obj = localSearchJson(json);
			if("movie".equals(getRequest()))
				obj = movieSearchJson(json);
			if("news".equals(getRequest()))
				obj = newsSearchJson(json);
			if("shop".equals(getRequest()))
				obj = shopSearchJson(json);
			if("webkr".equals(getRequest()))
				obj = webSearchJson(json);
		}
		
		return obj;
	}
	
	/*
	 * blog 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverBlog> blogSearchXml(Element element) {
		LinkedList<naverBlog> list = new LinkedList<naverBlog>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverBlog blog = new naverBlog();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				node = node.getNextSibling();
				String bloggername = node.getTextContent();
				node = node.getNextSibling();
				String bloggerlink = node.getTextContent();
				node = node.getNextSibling();
				String postdate = node.getTextContent();
				
				blog.setTitle(title);
				blog.setLink(link);
				blog.setDescription(description);
				blog.setBloggername(bloggername);
				blog.setBloggerlink(bloggerlink);
				blog.setPostdate(postdate);
				list.add(blog);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * blog 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverBlog> blogSearchJson(JSONObject json) {
		LinkedList<naverBlog> list = new LinkedList<naverBlog>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverBlog blog = new naverBlog();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String description = ja.getJSONObject(i).getString("description");
				String bloggername = ja.getJSONObject(i).getString("bloggername");
				String bloggerlink = ja.getJSONObject(i).getString("bloggerlink");
				String postdate = ja.getJSONObject(i).getString("postdate");
				
				blog.setTitle(title);
				blog.setLink(link);
				blog.setDescription(description);
				blog.setBloggername(bloggername);
				blog.setBloggerlink(bloggerlink);
				blog.setPostdate(postdate);
				list.add(blog);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * book 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverBook> bookSearchXml(Element element) {
		LinkedList<naverBook> list = new LinkedList<naverBook>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverBook book = new naverBook();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String image = node.getTextContent();
				node = node.getNextSibling();
				String author = node.getTextContent();
				node = node.getNextSibling();
				String price = node.getTextContent();
				node = node.getNextSibling();
				String discount = node.getTextContent();
				node = node.getNextSibling();
				String publisher = node.getTextContent();
				node = node.getNextSibling();
				String pubdate = node.getTextContent();
				node = node.getNextSibling();
				String isbn = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				
				book.setTitle(title);
				book.setLink(link);
				book.setImage(image);
				book.setAuthor(author);
				book.setPrice((price != null) ? Integer.parseInt(price) : 0);
				book.setDiscount((discount != null) ? Integer.parseInt(discount) : 0);
				book.setPublisher(publisher);
				book.setPubdate(pubdate);
				book.setIsbn(isbn);
				book.setDescription(description);
				list.add(book);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * Blog 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverBook> bookSearchJson(JSONObject json) {
		LinkedList<naverBook> list = new LinkedList<naverBook>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverBook book = new naverBook();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String image = ja.getJSONObject(i).getString("image");
				String author = ja.getJSONObject(i).getString("author");
				int price = 0;
				if(!ja.getJSONObject(i).getString("price").equals(""))
					price = ja.getJSONObject(i).getInt("price");
				int discount = 0;
				if(!ja.getJSONObject(i).getString("discount").equals(""))
					discount = ja.getJSONObject(i).getInt("discount");
				String publisher = ja.getJSONObject(i).getString("publisher");
				String pubdate = ja.getJSONObject(i).getString("pubdate");
				String isbn = ja.getJSONObject(i).getString("isbn");
				String description = ja.getJSONObject(i).getString("description");
				
				book.setTitle(title);
				book.setLink(link);
				book.setImage(image);
				book.setAuthor(author);
				book.setPrice(price);
				book.setDiscount(discount);
				book.setPublisher(publisher);
				book.setPubdate(pubdate);
				book.setIsbn(isbn);
				book.setDescription(description);
				list.add(book);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * cafe 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverCafearticle> cafeSearchXml(Element element) {
		LinkedList<naverCafearticle> list = new LinkedList<naverCafearticle>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverCafearticle cafe = new naverCafearticle();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				node = node.getNextSibling();
				String cafename = node.getTextContent();
				node = node.getNextSibling();
				String cafeurl = node.getTextContent();
				
				cafe.setTitle(title);
				cafe.setLink(link);
				cafe.setDescription(description);
				cafe.setCafename(cafename);
				cafe.setCafeurl(cafeurl);
				list.add(cafe);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * cafe 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverCafearticle> cafeSearchJson(JSONObject json) {
		LinkedList<naverCafearticle> list = new LinkedList<naverCafearticle>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverCafearticle cafe = new naverCafearticle();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String description = ja.getJSONObject(i).getString("description");
				String cafename = ja.getJSONObject(i).getString("cafename");
				String cafeurl = ja.getJSONObject(i).getString("cafeurl");
				
				cafe.setTitle(title);
				cafe.setLink(link);
				cafe.setDescription(description);
				cafe.setCafename(cafename);
				cafe.setCafeurl(cafeurl);
				list.add(cafe);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * encyc 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverEncyc> encycSearchXml(Element element) {
		LinkedList<naverEncyc> list = new LinkedList<naverEncyc>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverEncyc encyc = new naverEncyc();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				node = node.getNextSibling();
				String thumbnail = node.getTextContent();
				
				encyc.setTitle(title);
				encyc.setLink(link);
				encyc.setDescription(description);
				encyc.setThumbnail(thumbnail);
				list.add(encyc);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * encyc 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverEncyc> encycSearchJson(JSONObject json) {
		LinkedList<naverEncyc> list = new LinkedList<naverEncyc>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0 ; i < ja.length(); i++) {
				naverEncyc encyc = new naverEncyc();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String description = ja.getJSONObject(i).getString("description");
				String thumbnail = ja.getJSONObject(i).getString("thumbnail");
				
				encyc.setTitle(title);
				encyc.setLink(link);
				encyc.setDescription(description);
				encyc.setThumbnail(thumbnail);
				list.add(encyc);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * image 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverImage> imageSearchXml(Element element) {
		LinkedList<naverImage> list = new LinkedList<naverImage>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverImage image = new naverImage();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String thumbnail = node.getTextContent();
				node = node.getNextSibling();
				String sizeheight = node.getTextContent();
				node = node.getNextSibling();
				String sizewidth = node.getTextContent();
				
				image.setTitle(title);
				image.setLink(link);
				image.setThumbnail(thumbnail);
				image.setSizewidth(Integer.parseInt(sizewidth));
				image.setSizeheight(Integer.parseInt(sizeheight));
				list.add(image);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * image 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverImage> imageSearchJson(JSONObject json) {
		LinkedList<naverImage> list = new LinkedList<naverImage>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverImage image = new naverImage();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String thumbnail = ja.getJSONObject(i).getString("thumbnail");
				int sizewidth = ja.getJSONObject(i).getInt("sizewidth");
				int sizeheight = ja.getJSONObject(i).getInt("sizeheight");
				
				image.setTitle(title);
				image.setLink(link);
				image.setThumbnail(thumbnail);
				image.setSizewidth(sizewidth);
				image.setSizeheight(sizeheight);
				list.add(image);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * kin 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverKin> kinSearchXml(Element element) {
		LinkedList<naverKin> list = new LinkedList<naverKin>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverKin kin = new naverKin();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				
				kin.setTitle(title);
				kin.setLink(link);
				kin.setDescription(description);
				list.add(kin);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * kin 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverKin> kinSearchJson(JSONObject json) {
		LinkedList<naverKin> list = new LinkedList<naverKin>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverKin kin = new naverKin();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String description = ja.getJSONObject(i).getString("description");
				
				kin.setTitle(title);
				kin.setLink(link);
				kin.setDescription(description);
				list.add(kin);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * local 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverLocal> localSearchXml(Element element) {
		LinkedList<naverLocal> list = new LinkedList<naverLocal>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverLocal local = new naverLocal();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String category = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				node = node.getNextSibling();
				String telephone = node.getTextContent();
				node = node.getNextSibling();
				String address = node.getTextContent();
				node = node.getNextSibling();
				String roadAddress = node.getTextContent();
				node = node.getNextSibling();
				String mapx = node.getTextContent();
				node = node.getNextSibling();
				String mapy = node.getTextContent();
				
				local.setTitle(title);
				local.setLink(link);
				local.setCategory(category);
				local.setDescription(description);
				local.setTelephone(telephone);
				local.setAddress(address);
				local.setRoadAddress(roadAddress);
				local.setMapx(Integer.parseInt(mapx));
				local.setMapy(Integer.parseInt(mapy));
				list.add(local);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * local 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverLocal> localSearchJson(JSONObject json) {
		LinkedList<naverLocal> list = new LinkedList<naverLocal>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverLocal local = new naverLocal();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String category = ja.getJSONObject(i).getString("category");
				String description = ja.getJSONObject(i).getString("description");
				String telephone = ja.getJSONObject(i).getString("telephone");
				String address = ja.getJSONObject(i).getString("address");
				String roadAddress = ja.getJSONObject(i).getString("roadAddress");
				int mapx = ja.getJSONObject(i).getInt("mapx");
				int mapy = ja.getJSONObject(i).getInt("mapy");
				
				local.setTitle(title);
				local.setLink(link);
				local.setCategory(category);
				local.setDescription(description);
				local.setTelephone(telephone);
				local.setAddress(roadAddress);
				local.setRoadAddress(roadAddress);
				local.setMapx(mapx);
				local.setMapy(mapy);
				list.add(local);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * movie 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverMovie> movieSearchXml(Element element) {
		LinkedList<naverMovie> list = new LinkedList<naverMovie>(); 
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverMovie movie = new naverMovie();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String image = null;
				if(node.getNodeName().equals("image")) {
					image = node.getTextContent();
					node = node.getNextSibling();
				}
				String subtitle = node.getTextContent();
				node = node.getNextSibling();
				String pubDate = node.getTextContent();
				node = node.getNextSibling();
				String director = node.getTextContent();
				node = node.getNextSibling();
				String actor = node.getTextContent();
				node = node.getNextSibling();
				String userRating = node.getTextContent();
				
				movie.setTitle(title);
				movie.setLink(link);
				movie.setImage(image);
				movie.setSubtitle(subtitle);
				movie.setPubDate(pubDate);
				movie.setDirector(director);
				movie.setActor(actor);
				movie.setUserRating(Integer.parseInt(userRating));
				list.add(movie);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * movie 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverMovie> movieSearchJson(JSONObject json) {
		LinkedList<naverMovie> list = new LinkedList<naverMovie>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverMovie movie = new naverMovie();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String image = null;
				if(!ja.getJSONObject(i).isNull("image"))
					image = ja.getJSONObject(i).getString("image");
				String subtitle = ja.getJSONObject(i).getString("subtitle");
				String pubDate = ja.getJSONObject(i).getString("pubDate");
				String director = ja.getJSONObject(i).getString("director");
				String actor = ja.getJSONObject(i).getString("actor");
				int userRating = ja.getJSONObject(i).getInt("userRating");
				
				movie.setTitle(title);
				movie.setLink(link);
				movie.setImage(image);
				movie.setSubtitle(subtitle);
				movie.setPubDate(pubDate);
				movie.setDirector(director);
				movie.setActor(actor);
				movie.setUserRating(userRating);
				list.add(movie);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * news 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverNews> newsSearchXml(Element element) {
		LinkedList<naverNews> list = new LinkedList<naverNews>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverNews news = new naverNews();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String originallink = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				node = node.getNextSibling();
				String pubDate = node.getTextContent();
				
				news.setTitle(title);
				news.setOriginallink(originallink);
				news.setLink(link);
				news.setDescription(description);
				news.setPubDate(pubDate);
				list.add(news);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * news 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverNews> newsSearchJson(JSONObject json) {
		LinkedList<naverNews> list = new LinkedList<naverNews>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverNews news = new naverNews();
				String title = ja.getJSONObject(i).getString("title");
				String originallink = ja.getJSONObject(i).getString("originallink");
				String link = ja.getJSONObject(i).getString("link");
				String description = ja.getJSONObject(i).getString("description");
				String pubDate = ja.getJSONObject(i).getString("pubDate");
				
				news.setTitle(title);
				news.setOriginallink(originallink);
				news.setLink(link);
				news.setDescription(description);
				news.setPubDate(pubDate);
				list.add(news);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * shop 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverShop> shopSearchXml(Element element) {
		LinkedList<naverShop> list = new LinkedList<naverShop>();
		NodeList nList = element.getElementsByTagName("items");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverShop shop = new naverShop();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String image = null;
				if(node.getNodeName().equals("image")) {
					image = node.getTextContent();
					node = node.getNextSibling();
				}
				String lprice = node.getTextContent();
				node = node.getNextSibling();
				String hprice = node.getTextContent();
				node = node.getNextSibling();
				String mallName = node.getTextContent();
				node = node.getNextSibling();
				String productId = node.getTextContent();
				node = node.getNextSibling();
				String productType = node.getTextContent();
				
				shop.setTitle(title);
				shop.setLink(link);
				shop.setImage(image);
				shop.setLprice(Integer.parseInt(lprice));
				shop.setHprice(Integer.parseInt(hprice));
				shop.setMallName(mallName);
				shop.setProductId(Integer.parseInt(productId));
				shop.setProductType(Integer.parseInt(productType));
				list.add(shop);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * shop 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverShop> shopSearchJson(JSONObject json) {
		LinkedList<naverShop> list = new LinkedList<naverShop>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverShop shop = new naverShop();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String image = null;
				if(!ja.getJSONObject(i).isNull("image"))
					image = ja.getJSONObject(i).getString("image");
				int lprice = ja.getJSONObject(i).getInt("lprice");
				int hprice = ja.getJSONObject(i).getInt("hprice");
				String mallName = ja.getJSONObject(i).getString("mallName");
				int productId = ja.getJSONObject(i).getInt("productId");
				int productType = ja.getJSONObject(i).getInt("productType");
				
				shop.setTitle(title);
				shop.setLink(link);
				shop.setImage(image);
				shop.setLprice(lprice);
				shop.setHprice(hprice);
				shop.setMallName(mallName);
				shop.setProductId(productId);
				shop.setProductType(productType);
				list.add(shop);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * web 검색 결과를 xml형식으로 파싱하는 메소드
	 */
	public List<naverWebkr> webSearchXml(Element element) {
		LinkedList<naverWebkr> list = new LinkedList<naverWebkr>();
		NodeList nList = element.getElementsByTagName("item");
		
		if(nList != null) {
			for(int i = 0; i < nList.getLength(); i++) {
				naverWebkr web = new naverWebkr();
				Node node = nList.item(i).getFirstChild();
				String title = node.getTextContent();
				node = node.getNextSibling();
				String link = node.getTextContent();
				node = node.getNextSibling();
				String description = node.getTextContent();
				
				web.setTitle(title);
				web.setLink(link);
				web.setDescription(description);
				list.add(web);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	/*
	 * web 검색 결과를 json형식으로 파싱하는 메소드
	 */
	public List<naverWebkr> webSearchJson(JSONObject json) {
		LinkedList<naverWebkr> list = new LinkedList<naverWebkr>();
		
		if(!json.isNull("items")) {
			JSONArray ja = json.getJSONArray("items");
			
			for(int i = 0; i < ja.length(); i++) {
				naverWebkr web = new naverWebkr();
				String title = ja.getJSONObject(i).getString("title");
				String link = ja.getJSONObject(i).getString("link");
				String description = ja.getJSONObject(i).getString("description");
				
				web.setTitle(title);
				web.setLink(link);
				web.setDescription(description);
				list.add(web);
			}
		}
		else
			System.out.println("검색된 결과가 없습니다.");
		
		return list;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getHost() {
		return host;
	}

	public String getMethod() {
		return method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		
		if(!"".equals(this.format) && !"".equals(getRequest()))
			this.address += "." + this.format;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
		
		this.address = this.host + this.request;
		if(!"".equals(getFormat()))
			this.address += "." + getFormat();
		if(!"".equals(getQuery()))
			this.address += "?query=" + getQuery();
		if(!"".equals(getDisplay()))
			this.address += "&display=" + getDisplay();
		if(!"".equals(getStart()))
			this.address += "&start=" + getStart();
		if(!"".equals(getSort()) && !"webkr".equals(getRequest()))
			this.address += "&sort=" + getSort();
		if(!"".equals(getFilter()) && "image".equals(getRequest()))
			this.address += "&filter=" + getFilter();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
		
		if(!"".equals(getRequest()))
			this.address = getHost() + getRequest();
		if(!"".equals(this.query) && !"".equals(getRequest()))
			this.address += "?query=" + this.query;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
		
		if(!"".equals(this.display) && !"".equals(getQuery()))
			this.address += "&display=" + this.display;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
		
		if(!"".equals(this.start) && !"".equals(getQuery()))
			this.address += "&start=" + this.start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
		
		if(!"".equals(this.sort) && !"".equals(getQuery()) && !"webkr".equals(getRequest()))
			this.address += "&sort=" + this.sort;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
		
		if(!"".equals(this.filter) && !"".equals(getQuery()) && "image".equals(getRequest()))
			this.address += "&filter=" + this.filter;
	}
	
}