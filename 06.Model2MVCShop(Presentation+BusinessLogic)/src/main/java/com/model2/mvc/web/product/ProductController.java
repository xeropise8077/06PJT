package com.model2.mvc.web.product;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;



//==> 회원관리 Controller
@Controller
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addProduct.do")
	public String addProduct( @ModelAttribute("product") Product product) throws Exception {

		System.out.println("/addProduct.do");
		System.out.println(product.getManuDate().replace("-", ""));
		product.setManuDate( product.getManuDate().replace("-", "") );
		productService.addProduct(product);
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/listProduct.do")
	public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		System.out.println(map.get("list"));
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", (String)request.getParameter("menu"));
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/listProductVanila.do")
	public String listProductVanila(@ModelAttribute("search") Search search , Model model , HttpServletRequest request ) throws Exception {
		
		System.out.println("/listProductVanila.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		// Business logic 수행
		Map<String , Object> map=productService.getProductListVanila(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		System.out.println(map.get("list"));
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);	
		return "forward:/product/listProductVanila.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct( @RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		System.out.println("/getProduct.do");
		
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i] == null) {
				System.out.println("cookie null : " +request.getParameter("prodNo"));
				cookies[i] = new Cookie("history", request.getParameter("prodNo"));
				cookies[i].setMaxAge(-1);
				response.addCookie(cookies[i]);
			}
			else if (cookies[i] != null && cookies[i].getName().equals("history")) {
				System.out.println("cookie value : " + cookies[i].getValue());
				cookies[i] = new Cookie("history", cookies[i].getValue()+","+request.getParameter("prodNo"));
				cookies[i].setMaxAge(-1);
				response.addCookie(cookies[i]);
			}else {
				System.out.println("else : " + request.getParameter("prodNo"));
				cookies[i] = new Cookie("history", request.getParameter("prodNo"));
				cookies[i].setMaxAge(-1);
				response.addCookie(cookies[i]);
			}
			
		}		
		//Business Logic
		Product product =productService.getProduct(prodNo);
		// Model 과 View 연결
		
		model.addAttribute("product", product);
		model.addAttribute("menu", (String)request.getParameter("menu"));
		if(request.getParameter("menu").equals("manage")) {
			return "forward:/product/readProduct.jsp";
		}else {
			return "forward:/product/productDetail.jsp";
		}
	}	

	
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product") Product product, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("/updateProduct.do");
		//Business Logic
		
		
		
		int i =productService.updateProduct(product); 
		if(i == 1) {
			System.out.println("[ update가 완료되었습니다. ]");
		}else {
			System.out.println("[ update가 안됐습니다. ]");
		}
		
		// Model 과 View 연결
		model.addAttribute("product", productService.getProduct(product.getProdNo()));
		model.addAttribute("menu", (String)request.getParameter("menu"));
			return "forward:/product/productDetail.jsp";
			
	}		

}