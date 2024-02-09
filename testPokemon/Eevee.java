package testPokemon;

class Eevee extends Pokemon { 
	//イーブイの情報
	static final String NAME = "Eievui";
	final String[] ARRAY_ABILITY = {"Nigeashi","Tekiouryoku"};
	//進化ポケモンの情報
	//進化の石
	final String[] ARRAY_EVOLUTIONARY_STONE = {"WaterStone", "ThunderStone", "FireStone"};
	//0:DexNo 1:Name 2:Type 3:Ability 4:HP_MAX 5:Height 6:Weight
	final Object[] VAPOREON = {134, "Showers", ARRAY_TYPE[2], "Chosui", 1.0, 29.0, 130};
	final Object[] JOLTEON = {135, "Thunders", ARRAY_TYPE[3], "Chikuden", 0.8, 24.5, 65};
	final Object[] FLAREON = {136, "Booster", ARRAY_TYPE[1], "Moraibi", 0.9, 25.0, 65};
	final Object[][] ARRAY_EVOLVUTION = {VAPOREON, JOLTEON, FLAREON};
	// final int[] ARRAY_EVOLVED_DEXNO = {0134, 0135, 0136};
	// final String[] ARRAY_EVOLVED_NAME = {"Vaporeon", "Jolteon", "FireStone"};
	// final String[] ARRAY_EVOLVED_TYPE = {ARRAY_TYPE[2], ARRAY_TYPE[3], ARRAY_TYPE[1]};
	// final String[] ARRAY_EVOLVED_ABILITY = {"Chosui", "Chikuden", "Moraibi"};

	public Eevee() {
		this(NAME);
	}

	public Eevee(String nickname) {
		super(nickname);
		this.name = NAME;
		//87.5%♂・12.5%♀
		int num1 = this.rand.nextInt(2) + 1; //性別設定用
		this.gender = ARRAY_GENDER[num1];
		this.type = ARRAY_TYPE[0]; //Normal
		int num2 = this.rand.nextInt(ARRAY_ABILITY.length); //特性設定用
		this.ability = ARRAY_ABILITY[num2];
		this.ball = ARRAY_IMG_BALL[1];
		this.dexNo = 133;
		this.level = 1;
		this.hp_max = 55;
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
		for(int i = 0; i<ARRAY_EVOLUTIONARY_STONE.length; i++){
			if (stone.equals(ARRAY_EVOLUTIONARY_STONE[i])) {
				System.out.println("Congratulations! Your " + this.nickname + " evolved into " + ARRAY_EVOLUTION[i][1] + "!");
				this.dexNo = ARRAY_EVOLUTION[i][0];
				this.name = ARRAY_EVOLUTION[i][1];
				this.type = ARRAY_EVOLUTION[i][2];
				this.ability = ARRAY_EVOLUTION[i][3];
				this.height = ARRAY_EVOLUTION[i][4];
				this.weight = ARRAY_EVOLUTION[i][5];
				return;
			}
		}
		System.out.println(this.nickname + " received " + stone + ".");
		this.item = stone;
		
		// int num = -1; //要素番号
		// if (stone.equals(ARRAY_EVOLUTIONARY_STONE[0])) {
		// 	num = 0;
		// } else if (stone.equals(ARRAY_EVOLUTIONARY_STONE[1])) {
		// 	num = 1;
		// } else if (stone.equals(ARRAY_EVOLUTIONARY_STONE[2])) {
		// 	num = 2;
		// } else {
		// 	System.out.println("There doesn't seem to be any change.");
		// 	return;
		// }
		// System.out.println("Congratulations! Your " + this.nickname + " evolved into " + ARRAY_EVOLVED_NAME[num] + "!");
	}
}