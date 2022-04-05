package WebShop.Service.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WebShop.Dao.BillsDao;
import WebShop.Dto.Bills;
import WebShop.Dto.BillsDetail;
import WebShop.Dto.Cart;

@Service
public class BillServiceImpl implements IBillService {
	@Autowired
	private BillsDao billsDao;

	@Override
	public int AddBill(Bills bill) {
		return billsDao.AddBills(bill);
	}

	@Override
	public void AddBillsDetail(HashMap<Long, Cart> carts) {
		long idBills = billsDao.GetIDLastBills();
		for (Map.Entry<Long, Cart> itemCart : carts.entrySet()) {
			BillsDetail billsDetail = new BillsDetail();
			billsDetail.setId_bills(idBills);
			billsDetail.setId_product(itemCart.getValue().getProduct().getId());
			billsDetail.setQuanty(itemCart.getValue().getQuanty());
			billsDetail.setTotal(itemCart.getValue().getTotalPrice());
			billsDao.AddBillsDetail(billsDetail);
		}

	}
	//-=-=-=-=admin-=-=-=-=-=-
	@Override
	public List<Bills> GetBills() {
		return 	billsDao.GetBills();
	}
	@Override
	public List<Bills> GetBillByID(long id) {
		return 	billsDao.GetBillByID(id);
	}

}
