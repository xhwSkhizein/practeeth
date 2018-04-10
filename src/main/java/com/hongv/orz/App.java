package com.hongv.orz;

import com.hongv.orz.annotation.*;
import org.springframework.beans.factory.BeanFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by hongweixu at 2018/4/4 20:37
 */
public class App {


    public static void main(String[] args) {


        System.exit(0);
    }
}


class User {
    private final long id;
    private final String name;
    private final long createAt;

    public User(long id, String name, long createAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCreateAt() {
        return createAt;
    }
}


@DAO(dataSource = "User", slaveDataSource = "UserSlave")
interface UserDAO {

    @Insert(fields = {"name", "createAt"})
    int insert(long name, long createAt);

    @GetByIds
    Map<Long, User> getByIds(Collection<Long> ids);

    @UseSlave
    @GetByCursor
    List<User> getByCursor(Long offsetId, int limit);

    @FindByField("name")
    List<User> findByName(String name, Long offsetId, int limit);

    @Find(value = "select * from user where id < :id and name like :name", params = {"id", "name"})
    List<User> findBySql(long id, String name);
}