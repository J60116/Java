package ATMsystem;

import java.time.LocalDate;

public class Account {
	//フィールド
	private String name;
	private long balance;
	private int freeCount;
	private LocalDate dateOfLastUse;

	//コンストラクタ
	public Account(String name, int balance) {
		this.setName(name);
		this.setBalance(balance);
		//無料取引回数と最終利用日の設定
		this.setFreeCount(3);
		this.setDateOfLastUse(LocalDate.of(2024, 1, 8));
	}

	@Override
	public String toString() {
		return String.format("口座名義: %s \n残高: %,d \n最終利用日: %s \n最終利用月の取引回数: %d",
				name, balance, dateOfLastUse, freeCount);
	}

	//メゾット
	//残高照会の表示
	public void dispBalance() {
		System.out.println("口座名義: " + this.getName());
		System.out.println("残高: " + String.format("%,d", this.getBalance()));

	}

	//手数料無料の取引回数をカウント
	public void count() {
		this.setFreeCount(getFreeCount() + 1);
	}

	//アクセサ
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		
		if (name == null || name.length() > 10) {
			throw new IllegalArgumentException("nameは10文字以下で設定してください");
		}
		this.name = name;
//		if(name.matches("[a-zA-Z]{1,10}")) {
//			this.name=name;
//		} else {
//			throw new IllegalArgumentException("nameは英字10文字以下で設定してください");
//		}
	}

	public long getBalance() {
		return this.balance;
	}

	public void setBalance(long balance) {
		if (balance < 0) {
			throw new IllegalArgumentException("balanceは0以上で設定してください");
		}
		this.balance = balance;
	}

	public int getFreeCount() {
		return this.freeCount;
	}
	//初期設定用
	public void setFreeCount(int freeCount) {
		if (freeCount < 0) {
			throw new IllegalArgumentException("freeCountは0以上で設定してください");
		}
		this.freeCount = freeCount;
	}
	//カウント用（オーバーロード）
	public void setFreeCount() {
		this.freeCount++;
	}

	public LocalDate getDateOfLastUse() {
		return this.dateOfLastUse;
	}

	public void setDateOfLastUse(LocalDate date) {
		this.dateOfLastUse = date;
	}

}
