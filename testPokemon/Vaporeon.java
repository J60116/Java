package testPokemon;

class Vaporeon extends Eevee{ 
	//シャワーズ
	static final String NAME = "Showers";

	public Vaporeon(){
		this(NAME);
	}

	public Vaporeon(String name){
		super(name);
		this.dexNo = super.ARRAY_EVOLVUTION[0][0];
		this.name = super.ARRAY_EVOLVUTION[0][1];
		this.type = super.ARRAY_EVOLVUTION[0][2];
		this.ability = super.ARRAY_EVOLVUTION[0][3];
		this.hp_max = super.ARRAY_EVOLVUTION[0][4];
		this.height = super.ARRAY_EVOLVUTION[0][5];
		this.weight = super.ARRAY_EVOLVUTION[0][6];
		this.hp = this.hp_max;
		// this.dexNo = ARRAY_EVOLVED_DEXNO[0];
		// this.name = super.ARRAY_EVOLVED_NAME[0];
		// this.type = super.ARRAY_EVOLVED_TYPE[0];
		// this.ability = ARRAY_EVOLVED_ABILITY[0];
		// this.ball = ARRAY_IMG_BALL[1];
		// this.hp_max = 130;
		// this.height = 1.0;
		// this.weight = 29.0;
	}

	@Override
	public void attack(Pokemon p) {
		System.out.println(this.name + " attacked" + p.name + "!");
		p.hp -= 10;
	}

	public void HydroPump(Pokemon p){
		System.out.println(this.name + " used Hydro Pump!");
		p.hp -= 20;
	}

	@Override
	public void evolve(String stone) {
		//何もしない
	}
}