package di;

public class Client {
    private final Dao dao;
    private final Service service;

    public Client(Dao dao, Service service) {
        this.dao = dao;
        this.service = service;
    }

    public void doSomething(){
        System.out.println("클라이언트 시작");
        dao.select();
        service.save();
        System.out.println("클라이언트 끝");
    }
}
