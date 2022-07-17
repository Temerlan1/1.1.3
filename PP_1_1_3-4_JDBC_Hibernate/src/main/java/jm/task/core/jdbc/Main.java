package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl u =new UserDaoJDBCImpl();
       u.createUsersTable();
       u.saveUser("Ivan","Ivanov", (byte) 22);
       u.saveUser("Petr","Petrov", (byte) 25);
       u.saveUser("Sidor","Sidorov", (byte) 33);
       u.saveUser("Temerlan","Utsiev", (byte) 22);
       u.getAllUsers();
       u.cleanUsersTable();
        u.dropUsersTable();
    }
}
