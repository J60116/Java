package ATMsystem;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM implements BankTeller{

	//フィールド
	static final String NAME_BANK = "ABC銀行"; //ATMの取り扱い銀行名
	static final long LIMIT_STOCK = 10_000_000; //ATMの利用制限保有金額
	static final long MIN_AMOUNT = 1000; //最低限度額
	static final long MAX_DEPOSIT_AMOUNT = 1_000_000; //預入の最大限度額
	static final long MAX_WITHDRAWAL_AMOUNT = 500_000; //引出の最大限度額
	final int HANDLING_CHARGE = 100; //支払手数料
	final int MAX_FREE_COUNT = 3; //無料上限回数
	private long stock; //ATM保有金額
	private String transaction; //取引内容
	private int transactionCode;//取引内容コード
	private long transactionAmount; //取引金額
	private boolean trade; //残高の変更を伴う取引の有無
	private boolean charge; //手数料の要否
	private boolean checked; //入力内容の確認
	Account account;
	Bankbook bankbook;
	Scanner sc;
	private LocalDate now;

	//コンストラクタ
	public ATM(long stock) {
		setStock(stock);
		setTransaction("取引なし");
		setTransactionCode(0);
		setTransactionAmount(0);
		sc = new Scanner(System.in);
		setTransactionDate();
	}

	//アクセサ
	public long getStock() {
		return this.stock;
	}

	public void setStock(long stock) {
		if (stock < LIMIT_STOCK) {
			throw new IllegalArgumentException("ATMの利用制限保有金額に足りません。処理を中断。");
		}
		this.stock = stock;
	}

	public String getTransaction() {
		return this.transaction;
	}

	public void setTransaction(String s) {
		this.transaction = s;
	}

	public int getTransactionCode() {
		return this.transactionCode;
	}

	public void setTransactionCode(int n) {
		this.transactionCode = n;
	}

	public long getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(long n) {
		this.transactionAmount = n;
	}

	public boolean getTrade() {
		return this.trade;
	}

	public void trueTrade() {
		//取引が成立した場合、口座の最終利用日を更新する
		this.account.setDateOfLastUse(getTransactionDate());
		this.bankbook.recording();
		this.falseChecked();
		this.trade = true;
	}

	public void falseTrade() {
		this.trade = false;
	}

	public boolean getCharge() {
		return this.charge;
	}

	public void setCharge(int transactionCode) {
		switch (transactionCode) {
		case 1 -> {
			//預入の場合
			if ((this.getTransactionAmount() < 30_000) && (this.account.getFreeCount() >= this.MAX_FREE_COUNT)) {
				this.charge = true;
			} else {
				this.charge = false;
			}
		}
		case 2 -> {
			//引出の場合
			if (this.account.getFreeCount() >= this.MAX_FREE_COUNT) {
				this.charge = true;
			} else {
				this.charge = false;
			}
		}
		}
	}

	public void falseCharge() {
		this.charge = false;
	}

	public boolean getChecked() {
		return this.checked;
	}

	public void trueChecked() {
		this.checked = true;
	}

	public void falseChecked() {
		this.checked = false;
	}

	public LocalDate getTransactionDate() {
		return this.now;
	}

	public void setTransactionDate() {
		this.now = LocalDate.now();
	}

	//メゾット
	//取引内容の選択
	public void start() {
		if (getStock() <= LIMIT_STOCK) {
			System.out.println("\n<<ただいまお取り扱いできません>>");
		} else {
			System.out.println("\n<<いらっしゃいませ>>");
			System.out.print("\n【1】お預入れ\n【2】お引き出し\n【3】残高照会\n【0】取引終了\n\nご希望の取引を選択してください: ");
			
			try {
				this.setTransactionCode(this.sc.nextInt());
				switch (this.getTransactionCode()) {
				case 0 -> this.finish();
				case 1 -> {
					this.setTransaction("預入");
					this.remind();
					this.deposit();
					this.choicePrint();
				}
				case 2 -> {
					this.setTransaction("引出");
					this.remind();
					this.withdrawal();
					this.choicePrint();
				}
				case 3 -> {
					this.setTransaction("残高照会");
					this.account.dispBalance();
					this.start2();
				}
				default -> this.stop();
				}
				;
			} catch(InputMismatchException e) {
				//発生した例外内容を表示する
				System.out.println(e.toString());
				//再入力させる
				System.out.println("入力文字列を整数へ変換できません。はじめからやり直してください。");
				//入力内容を取り消す
				this.sc.next();
			}
		}

	}

	public void start2() {
		System.out.print("\n取引を続けますか？\n【1】はい 【0】いいえ: ");
		int n = 0;
		try {
			n = this.sc.nextInt();
			switch (n) {
			case 0 -> {
				this.finish();
			}
			case 1 -> {
				this.start();
			}
			default -> {
				this.stop();
			}
			}
		} catch(InputMismatchException e){
			this.stop();
			this.sc.next();
		}
	}

	//利用手数料の注意文を表示
	public void remind() {
		System.out.println("---------------------------------------------");
		System.out.println("ご利用の際は手数料がかかる可能性があります");
		System.out.println(NAME_BANK + "のATM利用手数料は" + this.HANDLING_CHARGE + "円です");
		System.out.println("【預入(3万円未満)と引出の手数料は月" + this.MAX_FREE_COUNT + "回まで無料】");
		System.out.println(this.account.getName() + "は今月残り" + calcCount() + "回無料で利用できます");
		System.out.println("---------------------------------------------");
	}

	//無料で利用できる回数を計算
	public int calcCount() {
		//前回利用日から月が替わっていたら0に戻す
		if (this.account.getDateOfLastUse()
				.isBefore(LocalDate.of(this.getTransactionDate().getYear(), this.getTransactionDate().getMonth(), 1))) {
			this.account.setFreeCount(0);
		}
		int count = this.MAX_FREE_COUNT - this.account.getFreeCount();
		if (count >= 0) {
			return count;
		} else {
			return 0;
		}

	}
	
	public void finish() {
		System.out.println("\n取引を終了します\nご利用いただきましてありがとうございました");
	}
	
	public void stop() {
		this.setTransaction("取引はありません");
		this.setTransactionCode(0);
		this.setTransactionAmount(0);
		System.out.println("入力エラーのため取引を中止します\nはじめからやり直してください");
	}

	//任意の額を入金
	@Override
	public void deposit() {
		while (true) {
			System.out.print("\n入金額を入力してください:");
			try {
				this.setTransactionAmount(this.sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("整数値で入力してください");
				this.sc.nextLine();
				continue;
			}
			this.setCharge(this.transactionCode);
			//預入可能な金額か確認
			if (this.getTransactionAmount() < MIN_AMOUNT || this.getTransactionAmount() > MAX_DEPOSIT_AMOUNT) {
				System.out.println(MIN_AMOUNT + "～" + MAX_DEPOSIT_AMOUNT + "の間で入力してください");
				continue;
			}
			//手数料がかかる場合、残高がマイナスになるのを防ぐ →　取引最低限度額を設定したため不要
//			if (this.getCharge()) {
//				if (this.account.getBalance() + this.getTransactionAmount() - this.HANDLING_CHARGE < 0) {
//					System.out.println("残高が不足しています");
//					continue;
//				}
//			}
			this.check(this.getTransactionCode());
			//入力金額の確認を得てから、残高を変更する
			if (this.getChecked()) {
				this.setStock(this.getStock() + this.getTransactionAmount());
				this.account.setBalance(this.account.getBalance() + this.getTransactionAmount());
				if (this.getCharge()) {
					this.account.setBalance(this.account.getBalance() - this.HANDLING_CHARGE);
				}
				this.trueTrade();
				System.out.println(String.format("%,d", this.getTransactionAmount()) + "円を入金します");
			}
			break;
		}
		//入金では３万円以下の場合にカウントする
		if (this.getTransactionAmount() <= 30_000) {
			this.account.setFreeCount();
		}

	}

	//任意の額を出金
	@Override
	public void withdrawal() {
		if (this.account.getBalance() <= 0) {
			//残高が0以下の場合
			System.out.println("残高がありません");
			this.setTransaction("取引なし");
			this.setTransactionCode(0);
			this.setTransactionAmount(0);
			return;
		} else if (this.account.getBalance() < MIN_AMOUNT ||(this.account.getFreeCount() >= 3 && this.account.getBalance() - this.HANDLING_CHARGE < MIN_AMOUNT )) {
			//残高が取引最低限度額に満たない場合
			System.out.println("残高が不足しているため取引を中止します");
			this.setTransaction("取引なし");
			this.setTransactionCode(0);
			this.setTransactionAmount(0);
			return;
		}

		while (true) {
			System.out.print("\n出金額を入力してください:");
			try {
				this.setTransactionAmount(this.sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("整数値で入力してください");
				this.sc.nextLine();
				continue;
			}
			this.setCharge(this.getTransactionCode());
			//引出可能な金額か確認
			if (this.getTransactionAmount() < MIN_AMOUNT ||this.getTransactionAmount() > MAX_WITHDRAWAL_AMOUNT) {
				System.out.println(MIN_AMOUNT + "～" + MAX_WITHDRAWAL_AMOUNT + "の間で入力してください");
				continue;
			}
			//残高がマイナスになるのを防ぐ
			if (this.getCharge()) {
				if (this.account.getBalance() < this.getTransactionAmount() + this.HANDLING_CHARGE) {
					System.out.println("残高が不足しています");
					continue;
				}
			} else {
				if (this.account.getBalance() < this.getTransactionAmount()) {
					System.out.println("残高が不足しています");
					continue;
				}
			}
			this.check(this.getTransactionCode());
			//入力金額の確認を得てから、残高を変更する
			if (this.getChecked()) {
				this.setStock(this.getStock() - this.getTransactionAmount());
				this.account.setBalance(this.account.getBalance() - this.getTransactionAmount());
				if (this.getCharge()) {
					this.account.setBalance(this.account.getBalance() - this.HANDLING_CHARGE);
				}
				this.trueTrade();
				System.out.println(String.format("%,d", this.getTransactionAmount()) + "円を出金します");
			}
			break;
		}
		this.account.setFreeCount();
	}

	//手数料が無料の取引回数
//	public void judge(int transactionCode) {
//		switch (transactionCode) {
//		case 1 -> {
//			if ((this.transactionAmount < 30_000) && (this.account.getFreeCount() >= this.MAX_FREE_COUNT)) {
//				this.trueCharge();
//			}
//		}
//		case 2 -> {
//			if (this.account.getFreeCount() >= this.MAX_FREE_COUNT) {
//				this.trueCharge();
//			}
//		}
//		}
//	}

	//入力した金額の確認
	public void check(int transactionCode) {
		System.out.println("---------------------------------------------");
		System.out.println("金額: " + String.format("%,d", this.getTransactionAmount()) + " 円");
		System.out.println("---------------------------------------------");
		System.out.print("ご確認のうえ、\n【1】確認 または【0】取消\nを選択してください: ");
		int n = 0;
		try {
			n = this.sc.nextInt();
			switch (n) {
			case 0 -> {
				this.falseChecked();
				if (transactionCode == 1) {
					this.deposit();
				} else if (transactionCode == 2) {
					this.withdrawal();
				}
			}
			case 1 -> this.trueChecked();
			default -> {
				this.falseChecked();
				this.stop();
			}
			}
			;
		} catch(InputMismatchException e){
			this.falseChecked();
			this.stop();
			this.sc.next();
		}
	}

	//利用明細書の発行
	public void choicePrint() {
		//取引がない場合は発行しない
		if (this.getTrade() == false) {
			return;
		}
		System.out.print("\nご利用明細表を発行する場合は\n【1】を入力してください: ");
		String s = this.sc.next();
		if(s.equals("1")) {
			this.createStatement();
		} else {
			System.out.println("\n<<ご利用ありがとうございました>>");
		}
		this.setTransaction("取引なし");
		this.setTransactionCode(0);
		this.setTransactionAmount(0);
		this.falseCharge();
		this.falseTrade();
	}

	//利用明細表の作成
	@Override
	public void createStatement() {
		System.out.println("\n********ご利用明細表********");
		System.out.println("　お取引日: " + this.getTransactionDate());
		System.out.println("　口座名義: " + this.account.getName());
		System.out.println("お取引内容: " + this.getTransaction());
		System.out.println("お取引金額: " + String.format("%,13d", this.getTransactionAmount()));
		System.out.println("　　　残高: " + String.format("%,13d", this.account.getBalance()));
		System.out.println("ご利用ありがとうございました");
		for (int i = 0; i < (26 - NAME_BANK.length()) / 2; i++) {
			System.out.print("*");
		}
		System.out.print(NAME_BANK);
		for (int i = 0; i < (26 - NAME_BANK.length()) / 2; i++) {
			System.out.print("*");
		}
		System.out.println("");
	}
}