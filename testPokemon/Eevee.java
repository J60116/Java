package testPokemon;

class Eevee extends Pokemon {
	//イーブイの情報
	static final String NAME = "Eievui";
	static final String[] ARRAY_ABILITY = { "Nigeashi", "Tekiouryoku" };
	//Object[] EEVEE = { 133, "Eievui", ARRAY_TYPE[0], "Nigeashi", 0.3, 6.5, 55 };
	//進化の石
	static final String[] ARRAY_EVOLUTIONARY_STONE = { "WaterStone", "ThunderStone", "FireStone" };
	//進化ポケモンの情報
	//0:DexNo 1:Name 2:Type 3:Ability 4:Height 5:Weight 6:HP_MAX
	//static final Object[] VAPOREON = {134, "Showers", ARRAY_TYPE[2], "Chosui", 1.0, 29.0, 130};
	//static final Object[] JOLTEON = {135, "Thunders", ARRAY_TYPE[3], "Chikuden", 0.8, 24.5, 65};
	//static final Object[] FLAREON = {136, "Booster", ARRAY_TYPE[1], "Moraibi", 0.9, 25.0, 65};
	//static final Object[][] ARRAY_EVOLUTION = {VAPOREON, JOLTEON, FLAREON};
	static final int[] ARRAY_EVOLVED_DEXNO = { 0134, 0135, 0136 };
	static final String[] ARRAY_EVOLVED_NAME = { "Showers", "Thunders", "Booster" };
	static final String[] ARRAY_EVOLVED_TYPE = { ARRAY_TYPE[2], ARRAY_TYPE[3], ARRAY_TYPE[1] };
	static final String[] ARRAY_EVOLVED_ABILITY = { "Chosui", "Chikuden", "Moraibi" };
	static final double[][] ARRAY_EVOLVED_HW = { { 1.0, 29.0 }, { 0.8, 24.5 }, { 0.9, 25.0 } };
	static final int[] ARRAY_EVOLVED_MAXHP = { 130, 65, 65 };

	public Eevee() {
		this(NAME);
	}

	public Eevee(String nickname) {
		super(nickname);
		this.name = NAME;
		this.setGender();
		this.type = ARRAY_TYPE[0]; //Normal
		this.setAbility();
		this.ball = ARRAY_IMG_BALL[1];
		this.dexNo = 133;
		this.level = 1;
		this.hp_max = 55;
		this.hp = this.hp_max;
		this.height = 0.3;
		this.weight = 6.5;
	}

	public void setGender() {
		//87.5%♂・12.5%♀
		int num = this.rand.nextInt(8); //性別設定用
		if (num != 0) {
			this.gender = ARRAY_GENDER[1];
		} else {
			this.gender = ARRAY_GENDER[2];
		}
	}
	
	public void setAbility() {
		int num = this.rand.nextInt(ARRAY_ABILITY.length); //特性設定用
		this.ability = ARRAY_ABILITY[num];
	}
	
	@Override
	public void setItem(String item) {
		//持たせたアイテムが進化の石の場合
		for (int i = 0; i < ARRAY_EVOLUTIONARY_STONE.length; i++) {
			if (item.equals(ARRAY_EVOLUTIONARY_STONE[i])) {
				this.evolve(i);
				super.setItem("None");
				return;
			}
		}
		System.out.println(this.nickname + " received " + item + ".");
		super.setItem(item);
	}

	@Override
	public void attack(Pokemon p) {
		System.out.println(this.name + "attacked" + p.name + "!");
		p.hp -= 5;
	}

	//進化
	@Override
	public void evolve(int i) {
		System.out.println("Congratulations! Your " + this.nickname + " evolved into " + ARRAY_EVOLVED_NAME[i] + "!");
		this.dexNo = ARRAY_EVOLVED_DEXNO[i];
		this.name = ARRAY_EVOLVED_NAME[i];
		this.type = ARRAY_EVOLVED_TYPE[i];
		this.ability = ARRAY_EVOLVED_ABILITY[i];
		this.height = ARRAY_EVOLVED_HW[i][0];
		this.weight = ARRAY_EVOLVED_HW[i][1];
		this.hp_max = ARRAY_EVOLVED_MAXHP[i];
		
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
	}}