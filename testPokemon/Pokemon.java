package testPokemon;

import java.util.Random;

abstract class Pokemon {
	
	//タイプ
	final String[] ARRAY_TYPE = {"NORMAL","FIRE","WATER","ELECTRIC","GRASS","ICE","FIGHTING","POISON","GROUND","FLYING","PSYCHIC","BUG","ROCK","GHOST","DRAGON","DARK","STEEL","FAIRY"};
	//ボールの種類(0:野生、4:敵)
	final String[] ARRAY_BALL = {"Wild","Monster ball","Super ball","Master ball","Enemy"};
	//ボールの画像(0:ボールなし（野生または敵）1:戦闘可能、2:瀕死状態）
	final String[] ARRAY_IMG_BALL = {"・","○","●"};
	//性別
	final String[] ARRAY_GENDER = {"Unknown","Male","Female"};
	final String[] ARRAY_IMG_GENDER = {"・","♂","♀"};
	//技の効果
	final String[] ARRAY_EFFECTIVE_MSG = {"Has no effect","Not very effective","Effective","Very effective"};
	final double[] ARRAY_EFFECTIVE_RATE = {0.0, 0.5, 1.0, 2.0};
	String name; //名前
	String nickname; //ニックネーム
	String gender; //性別
	String type; //タイプ
	String ability; //特性
	String ball; //ボール
	String item; //もちもの
	int dexNo; //ずかん番号
	int level; //レベル
	int hp; //体力
	int hp_max; //最大HP
	int exp; //経験値
	double height; //高さ(m)
	double weight; //重さ(kg)
	Random rand; //乱数用
	
	public Pokemon(String nickname) {
		this.nickname = nickname;
		this.name = "Unknown";
		this.gender = ARRAY_GENDER[0];
		this.type = ARRAY_TYPE[0];
		this.ability = "Unknown";
		this.ball = ARRAY_IMG_BALL[0];
		this.item = "None";
		this.dexNo = 0;
		this.level = 1;
		this.hp_max = 1;
		this.hp = this.hp_max;
		this.exp = 0;
		this.height = 1.0;
		this.weight = 1.0;
		this.rand = new Random();
	}

	public String toString() {
		String str = this.ball + this.nickname + "(" + this.name + ") Lv." + this.level
				+ "\nType: " + this.type
				+ "\nHP: " + this.hp + "/" + this.hp_max
				+ "\nExp.Points: " + this.exp;
		return str;
	}

	//ステータスを表示する
	public void showStatus() {
		System.out.println(this.toString());
	}
		
	//逃げる
	public void run() {
		System.out.println(this.nickname + " run away.");
	}

	//回復する
	public void recover(){
		this.hp = this.hp_max;
		System.out.println("Your " + this.nickname + " regained health!");
	}

	//抽象メゾット
	//戦う
	abstract void attack(Pokemon p);
	
}
