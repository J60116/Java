package testPokemon;

class Vaporeon extends Eevee{ 
	//シャワーズ
	static final String NAME = "Vaporeon";

	public Vaporeon(){
		this(NAME);
	}

	public Vaporeon(String name){
		super(name);
		this.name = super.ARRAY_EVOLVED_NAME[0];
		this.type = super.ARRAY_EVOLVED_TYPE[0];
		this.ability = ARRAY_EVOLVED_ABILITY[0];
		this.ball = ARRAY_IMG_BALL[1];
		this.dexNo = ARRAY_EVOLVED_DEXNO[0];
		this.hp_max = 130;
		this.hp = this.hp_max;
		this.height = 1.0;
		this.weight = 29.0;
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