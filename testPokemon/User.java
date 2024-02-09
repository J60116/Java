package testPokemon;

class User {
	String name;
	Pokemon[] pocket;
	
	public User() {
		this("Satoshi");
	}
	
	public User(String name) {
		this.name = name;
		this.pocket = new Pokemon[6];
	}
	
	public void getPokemon(Pokemon pokemon) {
		for(int i = 0; i<this.pocket.length; i++) {
			if(this.pocket[i] == null) {
				this.pocket[i] = pokemon;
				break;
			}
		}
//拡張for文は使用不可
//pはfor文の中で宣言した変数であり、pへの代入はthis.pocket自身の変更にはならない
//		for(Pokemon p : this.pocket) {
//			if(p==null) {
//				p = pokemon;
//				break;
//			}
//		}
	}

	public void visitPokemonCenter(){
		System.out.println(this.name + " visited the pokemon center.");
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
				p.showStatus();
			}
		}
		System.out.println("----------------------\n");
	}
	

}