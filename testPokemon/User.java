package testPokemon;

import java.util.Scanner;

class User {
	String name;
	Pokemon[] pocket;
	Pokemon[] box;
	Scanner sc;
	
	public User() {
		this("Satoshi");
	}
	
	public User(String name) {
		this.name = name;
		this.pocket = new Pokemon[6];
		this.box = new Pokemon[30];
		this.sc = new Scanner(System.in);
	}

	//ニックネームをつける
	public void setNickname(Pokemon pokemon){
		System.out.println("Do you give "+ pokemon.name +" a nickname?");
		System.out.print("【1】YES 【0】NO : ");
		int num = sc.nextInt();
		if(num == 1){
			System.out.print("Nickname: ");
			String str = sc.next();
			pokemon.nickname = str;
		}
		System.out.println("Pleasure to meet you, "+ pokemon.nickname + "!"); 
	}
	
	//ポケモンを捕まえる
	public void getPokemon(Pokemon pokemon) {
		System.out.println(this.name + " got " + pokemon.name + "!");
		for(int i = 0; i < this.pocket.length; i++) {
			if(this.pocket[i] == null) {
				this.pocket[i] = pokemon;
				setNickname(pokemon);
				System.out.println(this.name + " put " + pokemon.nickname + " in the pocket.");
				return;
			}
		}
		//ポケットに空きがない場合はボックスに転送する
		for(int i = 0; i < this.box.length; i++) {
			if(this.box[i] == null) {
				this.box[i] = pokemon;
				System.out.println(pokemon.name + " has moved into the box.");
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

	//ポケモンセンターに行く
	public void visitPokemonCenter(){
		System.out.println(this.name + " visited the pokemon center.");
		for(Pokemon p : this.pocket) {
			if(p!=null) {
				p.recover();
			}
		}
	}
	
	//所持しているポケモンのステータスを見る
	public void viewPartyStatus(){
		System.out.println("\nParty Status ---------");
		for(Pokemon p : this.pocket) {
			if(p!=null) {
				p.showStatus();
			}
		}
		System.out.println("----------------------\n");
	}
	
	//ポケモンにアイテムを持たせる
	public void givePokemonItem(Pokemon pokemon, String str) {
		pokemon.setItem(str);
	}
	
}