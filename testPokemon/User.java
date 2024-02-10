package testPokemon;

import java.util.Scanner;

class User {
	String name; //名前
	private Pokemon[] pocket; //ポケモンを格納するポケット
	Pokemon[] box; //ポケモンを格納するボックス
	Scanner sc; //文字入力用

	public User() {
		this("Satoshi");
	}

	public User(String name) {
		this.name = name;
		this.pocket = new Pokemon[6];
		this.box = new Pokemon[30];
		this.sc = new Scanner(System.in);
	}

	public Pokemon[] getPocket() {
		return this.pocket;
	}

	public void setPocket(int num, Pokemon pokemon) {
		//ポケモンがボールに入っていない場合
		if (pokemon.getBall().equals(Pokemon.ARRAY_IMG_BALL[0])
				|| pokemon.getBall().equals(Pokemon.ARRAY_IMG_BALL[Pokemon.ARRAY_IMG_BALL.length - 1])) {
			System.out.println("Please set " + pokemon.name + " in a Monster ball.");
			return;
		}
		//既にポケットにいるポケモンを指定した場合
		for (int i = 0; i < this.getPocket().length; i++) {
			if (this.getPocket()[i] != null && this.getPocket()[i].equals(pokemon)) {
				System.out.println(this.name + " already have this Pokemon in your pocket.");
				return;
			}
		}
		//指定したポケットが空いていない場合
		if (this.getPocket()[num] != null) {
			System.out.println("There is already a Pokemon in your designated pocket.");
			return;
		}

		this.pocket[num] = pokemon;

	}

	//ニックネームをつける
	public void giveNickname(Pokemon pokemon) {
		System.out.println("Do you give " + pokemon.name + " a nickname?");
		System.out.print("【1】YES 【0】NO : ");
		int num = sc.nextInt();
		String str = pokemon.name;
		if (num == 1) {
			while (str.equals(pokemon.name)) {
				if (num == 1) {
					System.out.print("Nickname: ");
					str = sc.next();
				}
			}
		}
		pokemon.nickname = str;
		System.out.println("Pleasure to meet you, " + pokemon.nickname + "!");
	}

	//ポケモンを捕まえる
	public void getPokemon(Pokemon pokemon, String ball) {
		pokemon.setBall(ball);
		System.out.println(this.name + " got " + pokemon.name + "!");
		for (int i = 0; i < this.getPocket().length; i++) {
			if (this.getPocket()[i] == null) {
				this.setPocket(i, pokemon);
				this.giveNickname(pokemon);
				System.out.println(this.name + " put " + pokemon.nickname + " in the pocket.");
				return;
			}
		}
		//ポケットに空きがない場合はボックスに転送する
		for (int i = 0; i < this.box.length; i++) {
			if (this.box[i] == null) {
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
	public void visitPokemonCenter() {
		System.out.println(this.name + " visited the pokemon center.");
		for (Pokemon p : this.pocket) {
			if (p != null) {
				p.recover();
			}
		}
	}

	//所持しているポケモンのステータスを見る
	public void viewPartyStatus() {
		System.out.println("\nParty Status ---------");
		for (Pokemon p : this.pocket) {
			if (p != null) {
				p.showStatus();
			}
		}
		System.out.println("----------------------\n");
	}

	//ポケモンにアイテムを持たせる
	public void giveItem(Pokemon pokemon, String item) {
		if (pokemon != null) {
			System.out.println(pokemon.nickname + " received " + item + ".");
			pokemon.setItem(item);
		} else {
			System.out.println("There was no Pokemon in your designated place.");
		}
	}

}
