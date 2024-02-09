package testPokemon;

class Eevee extends Pokemon { 
	//イーブイの情報
	static final String NAME = "Eevee";
	final String[] ARRAY_ABILITY = {"Nigeashi","Tekiouryoku"};
	//進化ポケモンの情報
	final int[] ARRAY_EVOLVED_DEXNO = {0134, 0135, 0136};
	final String[] ARRAY_EVOLVED_NAME = {"Vaporeon", "Jolteon", "Flareon"};
	final String[] ARRAY_EVOLVED_TYPE = {ARRAY_TYPE[2], ARRAY_TYPE[3], ARRAY_TYPE[1]};
	final String[] ARRAY_EVOLVED_ABILITY = {"Chosui", "Chikuden", "Moraibi"};
	final String[] ARRAY_EVOLUTIONARY_STONE = {"WaterStone", "ThunderStone", "FireStone"};

	public Eevee() {
		this(NAME);
	}

	public Eevee(String nickname) {
		super(nickname);
		this.name = NAME;
		int num1 = this.rand.nextInt(2) + 1; //性別設定用
		this.gender = ARRAY_GENDER[num1];
		this.type = ARRAY_TYPE[0]; //Normal
		int num2 = this.rand.nextInt(ARRAY_ABILITY.length); //特性設定用
		this.ability = ARRAY_ABILITY[num2];
		this.ball = ARRAY_IMG_BALL[1];
		this.dexNo = 0133;
		this.level = 1;
		this.hp_max = 55; //最大HPを20とする
		this.hp = this.hp_max;
		this.height = 0.3;
		this.weight = 6.5;
	}

	@Override
	public void attack(Pokemon p) {
		System.out.println(this.name + "attacked" + p.name + "!");
		p.hp -= 5;
	}
	
	//進化
	public void evolve(String stone) {
		int num = -1; //要素番号
		if (stone.equals(ARRAY_EVOLUTIONARY_STONE[0])) {
			num = 0;
		} else if (stone.equals(ARRAY_EVOLUTIONARY_STONE[1])) {
			num = 1;
		} else if (stone.equals(ARRAY_EVOLUTIONARY_STONE[2])) {
			num = 2;
		} else {
			System.out.println("There doesn't seem to be any change.");
			return;
		}
		System.out.println("Congratulations! Your " + this.nickname + " evolved into " + ARRAY_EVOLVED_NAME[num] + "!");
	}
}