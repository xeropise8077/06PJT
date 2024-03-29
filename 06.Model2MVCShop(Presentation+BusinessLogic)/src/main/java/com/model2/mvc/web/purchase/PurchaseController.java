package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.model2.mvc.service.domain.Payment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.payment.PaymentService;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;



//==> 회원관리 Controller
@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	private PurchaseService purchaseService;
	//setter Method 구현 않음
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;
	
	public PurchaseController(){
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
	
	
	@RequestMapping("/addPurchaseView.do")
	public String addPurchaseView( @RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request ) throws Exception {

		System.out.println("/addPurchaseView.do?");
		
		
		HttpSession session = request.getSession();
		
		Product product=productService.getProduct(prodNo);
		User user = (User)session.getAttribute("user");
		model.addAttribute("product", product);
		model.addAttribute("user", user);
		
		
		return "forward:/purchase/purchaseProduct.jsp";
	}
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase( @ModelAttribute("purchase") Purchase purchase, @RequestParam("prodNo") int prodNo, HttpServletRequest request) throws Exception {
		
		System.out.println("/addPurchase.do");
		int price = Integer.parseInt( request.getParameter("price"));
		int money = Integer.parseInt( request.getParameter("money") );
		int wishPay = Integer.parseInt( request.getParameter("wishPay") );
		int count = Integer.parseInt( request.getParameter("count") );
		
		// 갯수 * 재화 값
		int FinalPrice = price * count;
		int Finalmoney = money * count;
		int FinalwishPay = wishPay * count;
		

		
		// purchase에 변경된  money, pay 적용
		User user =(User)request.getSession().getAttribute("user");
		user =userService.getUser(user.getUserId());
		user.setMoney( user.getMoney()- Finalmoney);
		user.setWishPay( user.getWishPay()- FinalwishPay);
		

		
		
//		userService.updateUser(user); // tranSaction 적용 위해 주석처리
		purchase.setBuyer(user);
		
		
		// purchase에 변경된 stockCount 적용
		Product product = productService.getProduct(prodNo);
		product.setStockCount( product.getStockCount()-count );
//		productService.updateProduct(product); // tranSaction 적용 위해 주석처리
		
		
//		// 영수증 추가
//		Payment payment =paymentService.getPayment(0);
//		payment.setBuyer_id( user.getUserId() );
//		payment.setProdNo(prodNo);
//		payment.setPrice(FinalPrice);
//		payment.setPaymentByMoney(Finalmoney);
//		payment.setPaymentByWishPay(FinalwishPay);
//		System.out.println(payment);
//		
//		purchase.setBuyer(user);
//		purchase.setPurchaseProd(product);
		
		
		int i=purchaseService.addPurchase(purchase);
		if(i==1) {
			System.out.println("[ Purchase 추가 완료]");
		}else {
			System.out.println("[ Purchase 추가 실패]");
		}
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
		
	}

	@RequestMapping("/listPurchase.do")
	public String getListPurchase( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception {
		
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		// Business logic 수행
		User user =(User)request.getSession().getAttribute("user");
		Map<String , Object> map=purchaseService.getPurchaseList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode( @RequestParam("tranno") int tranNo, @RequestParam("trancode") String tranCode) throws Exception {
		
		System.out.println("/updateTranCode.do");

		Purchase purchase =purchaseService.getPurchase(tranNo);

		if(purchase.getTranCode().trim().equals("1")) {
			purchase.setTranCode("2");
		}else if(purchase.getTranCode().trim().equals("2")) {
			purchase.setTranCode("3");
		}
		
		System.out.println(purchase.getTranCode());

	
		int i =purchaseService.updateTranCode(purchase);
		if(i == 1) {
			System.out.println("배송상태 변경 완료");
		}else {
			System.out.println("배송상태 변경 실패");
		}
		
		return "redirect:index.jsp";
		
	}
}	
