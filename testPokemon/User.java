package testPokemon;

public class User {
	String name;
    Pokemon[] pocket;

	
	public User() {
		this("Satoshi");
	}
	
	public User(String name) {
		this.name = name;
		this.pocket = new Pokemon[6];
	}

    public void goToPokemonCenter(){
        for(Pokemon p : this.pocket) {
			p.recover();
		}
    }

}