package testPokemon;

public class Main {

	public static void main(String[] args) {
		//ポケモントレーナーの生成
		User user = new User();
	
		//いま所持しているポケモン
		user.setPocket(0,new Eevee("MonsterBall"));
		user.setPocket(1,new Eevee("MonsterBall"));
		user.setPocket(2,new Vaporeon("MonsterBall"));
		
		//イーブイが現れた
		Pokemon eevee = new Eevee();

		//ポケモンをスーパーボールで捕まえる
		user.getPokemon(eevee, "SuperBall");

		//所持しているポケモンのステータスを見る
		user.viewPartyStatus();

		//ポケモンセンターに行く
		user.visitPokemonCenter();

		//2番目と3番目のポケモンに「かみなりのいし」を渡す
		user.giveItem(user.getPocket()[1], "ThunderStone");
		user.giveItem(user.getPocket()[2], "ThunderStone");

		//所持しているポケモンのステータスを見る
		user.viewPartyStatus();

	}

}
