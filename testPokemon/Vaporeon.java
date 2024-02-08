package testPokemon;

public class Vaporeon extends Eevee{ //シャワーズ

	static final String NAME = "Eevee";

    public Vaporeon(){
        this(NAME);
    }

    public Vaporeon(String name){
        super(name);
        this.name = ARRAY_EVOLVED_NAME[0];
        this.type = ARRAY_EVOLVED_TYPE[0];
        this.ability = ARRAY_EVOLVED_ABILITY[0];
        this.dexNo = ARRAY_EVOLVED_DEXNO[0];
        this.hp_max = 130;
        this.hp = this.hp.max
        this.height = 1.0;
		this.weight = 29.0;
    }

	@Override
	void attack(Pokemon p) {
		System.out.println(this.name + " attacked" + p.name + "!");
		p.hp -= 10;
	}

    void HydroPump(Pokemon p){
        System.out.println(this.name + " used Hydro Pump!");
		p.hp -= 20;
    }

	@Override
	void viewStatus() {
		System.out.println(this.toString());
	}
}