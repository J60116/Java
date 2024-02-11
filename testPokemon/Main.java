package testPokemon;

public class Main {

	public static void main(String[] args) {
		//ポケモントレーナーの生成
		User user = new User();
	
		//いま所持しているポケモン
		// user.pocket[0] = new Eevee(); //Userインスタント生成時に代入済み
		user.setPocket(1, new Eevee("PokeBall"));
		user.setPocket(2, new Vaporeon("SuperBall"));

		//ポケモンが現れた
		Pokemon eevee = new Eevee();
		
		//ポケモンをマスターボールで捕まえる
		user.getPokemon(eevee, "MasterBall");

		//所持しているポケモンの確認
		user.viewCurrentParty();
		
		//ポケモンセンターに行く
		user.visitPokemonCenter();

		//2番目と3番目のポケモンに「かみなりのいし」を渡す
		user.giveItem(1 , "ThunderStone");
		user.giveItem(2 , "ThunderStone");

		//所持しているポケモンのステータスを見る
		user.viewCurrentParty();
    
	}
}
