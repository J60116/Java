package ATMsystem;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Bankbook {
	Account account;
	ATM atm;
	Map<String, Object> log;
	List<Map<String, Object>> bankbook;

	//コンストラクタ
	public Bankbook(Account account,ATM atm) {
		this.account = account;
		this.atm = atm;
		this.log = new LinkedHashMap<String, Object>();
		this.log.put("年月日", LocalDate.of(2024, 1, 8));
//		this.log.put("取扱店", null);
		this.log.put("お預り金額", String.format("%,11d", account.getBalance()));
		this.log.put("お支払金額", " (新規)    ");
		this.log.put("現在高", String.format("%,11d", account.getBalance()));
		
		this.bankbook = new LinkedList<Map<String, Object>>();
		this.bankbook.add(log);
	}
	
	//メゾット
	//記帳
	public void recording() {
		Map<String, Object> newlog = new LinkedHashMap<String, Object>();
		
		//手数料がかかる場合
		if(this.atm.getCharge()) {
			//1行目に手数料加算前の取引を記入
			newlog.put("年月日", this.account.getDateOfLastUse());
			if(this.atm.getTransactionCode()==1) {
				newlog.put("お預り金額", String.format("%,11d", this.atm.getTransactionAmount()));
				newlog.put("お支払金額", " (ATM入金) ");
			} else if(this.atm.getTransactionCode()==2) {
				newlog.put("お預り金額", " (ATM出金) ");
				newlog.put("お支払金額", String.format("%,11d", this.atm.getTransactionAmount()));
			}
			newlog.put("現在高", String.format("%,11d", this.account.getBalance()+this.atm.HANDLING_CHARGE));
			this.bankbook.add(newlog);
			//2行目に手数料を記入
			Map<String, Object> newlogCharge = new LinkedHashMap<String, Object>();
			newlogCharge.put("年月日", this.account.getDateOfLastUse());
			newlogCharge.put("お預り金額", " (手数料)  ");
			newlogCharge.put("お支払金額", String.format("%,11d", this.atm.HANDLING_CHARGE));
			newlogCharge.put("現在高", String.format("%,11d", this.account.getBalance()));
			this.bankbook.add(newlogCharge);
		} else {
			//手数料がかからない場合
			newlog.put("年月日", this.account.getDateOfLastUse());
			if(this.atm.getTransactionCode()==1) {
				newlog.put("お預り金額", String.format("%,11d", this.atm.getTransactionAmount()));
				newlog.put("お支払金額", " (ATM入金) ");
			} else if(this.atm.getTransactionCode()==2) {
				newlog.put("お預り金額", " (ATM出金) ");
				newlog.put("お支払金額", String.format("%,11d", this.atm.getTransactionAmount()));
			}
			newlog.put("現在高", String.format("%,11d", this.account.getBalance()));
			this.bankbook.add(newlog);
		}
		
	}
	//記帳内容の表示
	public void disp() {
		for(Object list : this.bankbook) {
			System.out.print(list+" ");
			System.out.println();
		}
	}
}
