

public class Loader
{
	public static void main(String[] args) {
		// создаем кошек, кормим, поим и смотрим на вес
		for (int i = 0; i < 5; i++) {
			Cat cat = new Cat();
			System.out.println("Cat № " + i);
			feedCatAndCheck(cat);
		}
		
		//убиваем кошек
		Cat feededCat = new Cat();
		System.out.println("Lets feed that cat to death!");
		feededCat.feed(10000d);
		System.out.println("Cat ate 100000 gr");
		System.out.println("Cat status: " + feededCat.getStatus());
		
		Cat meowDeadCat = new Cat();
		System.out.println("Lets meow one cat to death!");
		while (!meowDeadCat.getStatus().equals("Dead")) {
			meowDeadCat.meow();
		}
		System.out.println("Finally that cat is " + meowDeadCat.getStatus());
		
	}
	
	static void feedCatAndCheck(Cat cat) {
		double feedAmount = Math.random() * 300;
		double origWeight = cat.getWeight();
		cat.feed(feedAmount);
		cat.drink(feedAmount / 2);
		System.out.printf("Cats original weight is %.1f\nCat ate %.1f gr and drunk %.1f ml.\nNow its weight is %.1f\n", origWeight, feedAmount, feedAmount / 2, cat.getWeight());
		System.out.println(cat.getStatus());
		System.out.println("========================");
		
	}
	
	public static Cat getCat() {
		return new Cat();
	}
}