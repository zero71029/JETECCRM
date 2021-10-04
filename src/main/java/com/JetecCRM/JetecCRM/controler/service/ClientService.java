package com.JetecCRM.JetecCRM.controler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.JetecCRM.JetecCRM.model.ClientBean;
import com.JetecCRM.JetecCRM.model.ContactBean;
import com.JetecCRM.JetecCRM.model.MarketBean;
import com.JetecCRM.JetecCRM.repository.ClientRepository;
import com.JetecCRM.JetecCRM.repository.ContactRepository;
import com.JetecCRM.JetecCRM.repository.MarketRepository;

@Service
@Transactional
public class ClientService {

	@Autowired
	ClientRepository cr;
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	MarketRepository mr;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存客戶
	public void SaveAdmin(ClientBean clientBean) {
		cr.save(clientBean);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取客戶列表
	public Object getList() {
		return cr.findAll();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取客戶細節
	public ClientBean getById(Integer id) {
		return cr.getById(id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//儲存聯絡人
	public void SaveContact(ContactBean contactBean) {
		// 用公司名稱 去找id
		if (contactBean.getClientid() == null)
			contactBean.setClientid(cr.selectIdByname(contactBean.getCompany()));
		contactRepository.save(contactBean);

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取聯絡人列表
	public Object getContactList() {
		return contactRepository.findAll();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取聯絡人細節
	public Object getContactById(Integer id) {
		return contactRepository.getById(id);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取聯絡人by名稱 ajax
	public List<ContactBean> selectContactByClientName(String name) {
		return contactRepository.findByCompany(name);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//刪除聯絡人
	public void delMarket(List<Integer> id) {
		for (Integer i : id) {
			contactRepository.deleteById(i);
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 搜索聯絡人
	public List<ContactBean> selectContact(String name) {
		List<ContactBean> result= new ArrayList<ContactBean>();
		boolean boo = true;
		// 搜索名稱
		for (ContactBean p : contactRepository.findByNameLikeIgnoreCase("%" + name + "%")) {
			result.add(p);
		}

		// 用公司搜索
		for (ContactBean p : contactRepository.findByCompanyLikeIgnoreCase("%" + name + "%")) {
			for (ContactBean bean : result) {
				if (bean.getContactid() == p.getContactid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}
		// 用客戶搜索
		for (ContactBean p : contactRepository.findByPhoneLikeIgnoreCase("%" + name + "%")) {
			for (ContactBean bean : result) {
				if (bean.getContactid() == p.getContactid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}
		// 用聯絡人搜索
		for (ContactBean p : contactRepository.findByMoblieLikeIgnoreCase("%" + name + "%")) {
			for (ContactBean bean : result) {
				if (bean.getContactid() == p.getContactid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}

		return result;
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//搜索客戶
	public List<ClientBean> selectclient(String name) {
		List<ClientBean> result= new ArrayList<ClientBean>();
		boolean boo = true;
		// 搜索名稱
		for (ClientBean p : cr.findByNameLikeIgnoreCase("%" + name + "%")) {
			result.add(p);
		}

		// 用公司搜索
		for (ClientBean p : cr.findByUniformnumberLikeIgnoreCase("%" + name + "%")) {
			for (ClientBean bean : result) {
				if (bean.getClientid() == p.getClientid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}
		// 用客戶搜索
		for (ClientBean p : cr.findByPhoneLikeIgnoreCase("%" + name + "%")) {
			for (ClientBean bean : result) {
				if (bean.getClientid() == p.getClientid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}
		// 用聯絡人搜索
		for (ClientBean p : cr.findByUserLikeIgnoreCase("%" + name + "%")) {
			for (ClientBean bean : result) {
				if (bean.getClientid() == p.getClientid()) {
					boo = false;
				}
			}
			if (boo)
				result.add(p);
		}

		return result;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//讀取銷售機會by公司
	public List<MarketBean> getMarketListByClient(String name) {		
		return mr.findByClient(name);
	}

}
