package ATMsystem;

public interface BankTeller {
	void deposit(); //預入
	void withdrawal(); //引出
	public void createStatement(); //取引明細表の作成
}
