package ATMsystem;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Bankbook {
	Account account;
	ATM atm;
	final String[] COLUMN = { "年月日", "お預り金額", "お支払金額", "現在高" };
	final String FORMAT = "%,13d";
	Map<String, Object> log;
	List<Map<String, Object>> bankbook;

	//コンストラクタ
	public Bankbook(Account account, ATM atm) {
		this.account = account;
		this.atm = atm;
		this.log = new LinkedHashMap<String, Object>();
		this.log.put(COLUMN[0], LocalDate.of(2024, 1, 8));
		this.log.put(COLUMN[1], String.format(FORMAT, account.getBalance()));
		this.log.put(COLUMN[2], "  (新規)    ");
		this.log.put(COLUMN[3], String.format(FORMAT, account.getBalance()));
		this.bankbook = new LinkedList<Map<String, Object>>();
		this.bankbook.add(log);
	}

	//メゾット
	//記帳
	public void recording() {
		Map<String, Object> newlog = new LinkedHashMap<String, Object>();

		//手数料がかかる場合
		if (this.atm.getCharge()) {
			//1行目に手数料加算前の取引を記入
			newlog.put(COLUMN[0], this.account.getDateOfLastUse());
			if (this.atm.getTransactionCode() == 1) {
				newlog.put(COLUMN[1], String.format(FORMAT, this.atm.getTransactionAmount()));
				newlog.put(COLUMN[2], "  (ATM入金) ");
			} else if (this.atm.getTransactionCode() == 2) {
				newlog.put(COLUMN[1], "  (ATM出金) ");
				newlog.put(COLUMN[2], String.format(FORMAT, this.atm.getTransactionAmount()));
			}
			newlog.put(COLUMN[3], String.format(FORMAT, this.account.getBalance() + this.atm.HANDLING_CHARGE));
			this.bankbook.add(newlog);
			//2行目に手数料を記入
			Map<String, Object> newlogCharge = new LinkedHashMap<String, Object>();
			newlogCharge.put(COLUMN[0], this.account.getDateOfLastUse());
			newlogCharge.put(COLUMN[1], "  (手数料)  ");
			newlogCharge.put(COLUMN[2], String.format(FORMAT, this.atm.HANDLING_CHARGE));
			newlogCharge.put(COLUMN[3], String.format(FORMAT, this.account.getBalance()));
			this.bankbook.add(newlogCharge);
		} else {
			//手数料がかからない場合
			newlog.put(COLUMN[0], this.account.getDateOfLastUse());
			if (this.atm.getTransactionCode() == 1) {
				newlog.put(COLUMN[1], String.format(FORMAT, this.atm.getTransactionAmount()));
				newlog.put(COLUMN[2], "  (ATM入金) ");
			} else if (this.atm.getTransactionCode() == 2) {
				newlog.put(COLUMN[1], "  (ATM出金) ");
				newlog.put(COLUMN[2], String.format(FORMAT, this.atm.getTransactionAmount()));
			}
			newlog.put(COLUMN[3], String.format(FORMAT, this.account.getBalance()));
			this.bankbook.add(newlog);
		}

	}

	//記帳内容の表示
	public void disp() {
		//【1】{Key=Value}の形で表示
//			for(Object list : this.bankbook) {
//				System.out.println(list);
//			}
		
		//【2】1行目にKey、2行目以降にValueを表示
		for (String c : COLUMN) {
			System.out.print(String.format("【%s】 ", c));
		}
		System.out.println("");
		for (Map<String, Object> m : this.bankbook) {
			for (String key : m.keySet()) {
				Object value = m.get(key);
				System.out.print(value + " ");
			}
			System.out.println("");
		}
	}
}
