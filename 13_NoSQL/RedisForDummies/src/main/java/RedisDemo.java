import model.User;

import java.util.LinkedList;

public class RedisDemo {
	/*
	На сайте знакомств зарегистрировалось 20 пользователей. Показывайте их по очереди на главной странице сайта.

	По умолчанию порядок очереди зависит от времени регистрации на сайте. После каждого показа пользователь
	отправляется в конец очереди. Пользователь может оплатить услугу по перемещению в начало очереди.
	*/

    private static final int USERS_COUNT = 20;
    private static final int CYCLES = 100;
    LinkedList<Integer> usersList = new LinkedList<>();
    RedisRepository repository = RedisRepository.getRepository();
    static RedisDemo demo = new RedisDemo();

    public static void main(String[] args) {
        demo.addUsers();

        for (int i = 0; i < CYCLES; i++) {
            int randomUser = (int) (Math.random() * USERS_COUNT);
            if (Math.random() < 0.15) demo.paid(randomUser);
            System.out.println("User" + demo.getNext().getId() + " is shown");
        }
    }

    void addUsers() {
        for (int i = 0; i < USERS_COUNT; i++) {
            usersList.addLast(i);
            repository.addUser(new User(i, "User"+i));
        }
    }

    User getNext() {
        int result = usersList.remove();
        usersList.addLast(result);
        return repository.getUser(result);
    }

    void paid(int userIndex) {
        System.out.println("User" + userIndex + " made a purchase!");
        usersList.removeFirstOccurrence(userIndex);
        usersList.addFirst(userIndex);
    }
}
