package di;

public class Client {
    private final Dao dao;

    public Client(Dao dao) {
        this.dao = dao;
    }

    public void doSomething(){
        System.out.println("클라이언트 시작");
        dao.select();
        System.out.println("클라이언트 끝");
    }
}
