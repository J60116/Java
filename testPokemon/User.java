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
		System.out.println(this.name + " went to the pokemon center.");
		for(Pokemon p : this.pocket) {
			if(p!=null) {
				p.recover();
			}
		}
	}
	
	public void viewPartyStatus(){
		System.out.println("\nParty Status ---------");
		for(Pokemon p : this.pocket) {
			if(p!=null) {
				p.getStatus();
			}
		}
		System.out.println("----------------------\n");
	}

}
