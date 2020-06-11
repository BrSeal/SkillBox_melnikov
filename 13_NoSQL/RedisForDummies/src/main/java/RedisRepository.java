import model.User;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisRepository {

    private static final String KEY = "users";

    private static RedisRepository instance;
    private RedissonClient redisson;

    private RMap<Integer, User> users;

    private RedisRepository() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = Redisson.create(config);
        users = redisson.getMap(KEY);
    }

    public static RedisRepository getRepository() {
        if (instance == null) instance = new RedisRepository();
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public void shutdown() {
        redisson.shutdown();
    }


}
