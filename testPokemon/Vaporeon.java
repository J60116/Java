package testPokemon;

final class Vaporeon extends Eevee{ 
	//シャワーズ
	static final String NAME = "Showers";

	public Vaporeon(){
		this(ARRAY_IMG_BALL[0]);
	}

	public Vaporeon(String ball){
		super(ball);
//		this.dexNo = super.ARRAY_EVOLVUTION[0][0];
//		this.name = super.ARRAY_EVOLVUTION[0][1];
//		this.type = super.ARRAY_EVOLVUTION[0][2];
//		this.ability = super.ARRAY_EVOLVUTION[0][3];
//		this.hp_max = super.ARRAY_EVOLVUTION[0][4];
//		this.height = super.ARRAY_EVOLVUTION[0][5];
//		this.weight = super.ARRAY_EVOLVUTION[0][6];
		this.dexNo = ARRAY_EVOLVED_DEXNO[0];
		this.name = ARRAY_EVOLVED_NAME[0];
		this.nickname = this.name;
		super.setGender();
		this.type = ARRAY_EVOLVED_TYPE[0];
		this.ability = ARRAY_EVOLVED_ABILITY[0];
		this.height = ARRAY_EVOLVED_HW[0][0];
		this.weight = ARRAY_EVOLVED_HW[0][1];
		this.hp_max = ARRAY_EVOLVED_MAXHP[0];
		this.hp = this.hp_max;
	}
	
	@Override
	public void setItem(String item) {
		super.setItem(item);
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
	public void evolve(int i) {
		//何もしない
	}

}