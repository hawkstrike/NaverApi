import java.net.URLEncoder;
import java.util.LinkedList;

import naverConnection.naverConnection;
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

public class NaverApi {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			String clientId = "발급받은 clientId";
			String clientSecret = "발급받은 clientSecret";
			String request = "webkr";
			String format = "xml";
			String query = URLEncoder.encode("알고리즘", "UTF-8");
			String display = "10";
			String start = "1";
			
			LinkedList<naverBlog> blogList = new LinkedList<naverBlog>();
			LinkedList<naverBook> bookList = new LinkedList<naverBook>();
			LinkedList<naverCafearticle> cafeList = new LinkedList<naverCafearticle>();
			LinkedList<naverEncyc> encycList = new LinkedList<naverEncyc>();
			LinkedList<naverImage> imageList = new LinkedList<naverImage>();
			LinkedList<naverKin> kinList = new LinkedList<naverKin>();
			LinkedList<naverLocal> localList = new LinkedList<naverLocal>();
			LinkedList<naverMovie> movieList = new LinkedList<naverMovie>();
			LinkedList<naverNews> newsList = new LinkedList<naverNews>();
			LinkedList<naverShop> shopList = new LinkedList<naverShop>();
			LinkedList<naverWebkr> webList = new LinkedList<naverWebkr>();
			
			naverConnection nc = new naverConnection();
			
			nc = new naverConnection(clientId, clientSecret, format, request, query);
			//nc = new naverConnection(clientId, clientSecret, format, request, query, display, start);
			
			int responseCode = nc.getResponseCode();
			
			if(responseCode == 200) {
				String inputLine = nc.getResponseData();
				
				if("blog".equals(request))
					blogList = (LinkedList<naverBlog>) nc.getSearch(inputLine);
				if("book".equals(request))
					bookList = (LinkedList<naverBook>)nc.getSearch(inputLine);
				if("cafe".equals(request))
					cafeList = (LinkedList<naverCafearticle>)nc.getSearch(inputLine);
				if("encyc".equals(request))
					encycList = (LinkedList<naverEncyc>)nc.getSearch(inputLine);
				if("image".equals(request))
					imageList = (LinkedList<naverImage>)nc.getSearch(inputLine);
				if("kin".equals(request))
					kinList = (LinkedList<naverKin>)nc.getSearch(inputLine);
				if("local".equals(request))
					localList = (LinkedList<naverLocal>)nc.getSearch(inputLine);
				if("movie".equals(request))
					movieList = (LinkedList<naverMovie>)nc.getSearch(inputLine);
				if("news".equals(request))
					newsList = (LinkedList<naverNews>)nc.getSearch(inputLine);
				if("shop".equals(request))
					shopList = (LinkedList<naverShop>)nc.getSearch(inputLine);
				if("webkr".equals(request))
					webList = (LinkedList<naverWebkr>)nc.getSearch(inputLine);
			}
			
			// 각 클래스별 출력 방식 예제
			if(blogList != null) {
				for(int i = 0; i < blogList.size(); i++)
					System.out.println(blogList.get(i).toString());
			}
			if(bookList != null) {
				for(int i = 0; i < bookList.size(); i++)
					System.out.println(bookList.get(i).toString());
			}
			if(cafeList != null) {
				for(int i = 0; i < cafeList.size(); i++)
					System.out.println(cafeList.get(i).toString());
			}
			if(encycList != null) {
				for(int i = 0; i < encycList.size(); i++)
					System.out.println(encycList.get(i).toString());
			}
			if(imageList != null) {
				for(int i = 0; i < imageList.size(); i++)
					System.out.println(imageList.get(i).toString());
			}
			if(kinList != null) {
				for(int i = 0; i < kinList.size(); i++)
					System.out.println(kinList.get(i).toString());
			}
			if(localList != null) {
				for(int i = 0; i < localList.size(); i++)
					System.out.println(localList.get(i).toString());
			}
			if(movieList != null) {
				for(int i = 0; i < movieList.size(); i++)
					System.out.println(movieList.get(i).toString());
			}
			if(newsList != null) {
				for(int i = 0; i < newsList.size(); i++)
					System.out.println(newsList.get(i).toString());
			}
			if(shopList != null) {
				for(int i = 0; i < shopList.size(); i++)
					System.out.println(shopList.get(i).toString());
			}
			if(webList != null) {
				for(int i = 0; i < webList.size(); i++)
					System.out.println(webList.get(i).toString());
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}