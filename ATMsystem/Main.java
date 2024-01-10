package ATMsystem;

public class Main {

	public static void main(String[] args) {
		
		//口座とATMのインスタンスの生成
		Account a = new Account("a", 5_000_000);
		ATM atm = new ATM(11_000_000);
		//accountフィールドに生成済みのAccountインスタンスを代入
		atm.account = a;
		
		//口座の確認
		System.out.println(a);
		
		//取引開始
		atm.start();
		
		//続けて取引開始
		atm.start();
//		atm.start();
		
	}

}
