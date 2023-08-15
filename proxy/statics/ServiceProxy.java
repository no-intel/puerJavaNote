package proxy.statics;

public class ServiceProxy implements Service {
    private Service service;

    public ServiceProxy(){
        this.service = new ServiceImpl();
    }
    @Override
    public void save() {
        System.out.println("!!!!Transaction begin!!!!");
        try {
            service.save();
            System.out.println("@@@@@Commit@@@@");
        }catch (RuntimeException e){
            System.err.println("Caught exception in main: " + e.getMessage());
            System.out.println("####Roll Back####");
        }
    }
}
