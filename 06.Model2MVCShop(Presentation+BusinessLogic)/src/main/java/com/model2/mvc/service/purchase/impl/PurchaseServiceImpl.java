package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	
	@Autowired
	private PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {
		System.out.println("::"+getClass()+" default Constructor Call.....");
	}
	

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		System.out.println("::"+getClass()+".setProductDao Call......");
		this.purchaseDao=purchaseDao;
		
	}
	
	
	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		
		return purchaseDao.addPurchase(purchase);

		
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("list", purchaseDao.getPurchaseList(search, buyerId));
		map.put("totalCount", purchaseDao.getTotalCount(search, buyerId));
				
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePurchase(Purchase purchase) throws Exception {

		return purchaseDao.updatePurchase(purchase);
		
	}

	@Override
	public int updateTranCode(Purchase purchase) throws Exception {
		return purchaseDao.updateTranCode(purchase);
		
	}

}
