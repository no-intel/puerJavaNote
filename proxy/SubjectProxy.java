package proxy;

public class SubjectProxy implements Subject{
    private Subject subject;

    public SubjectProxy(){
        subject = new SubjectImpl();
    }
    @Override
    public void doSomething() {
        System.out.println("프록시 시작");
        subject.doSomething();
        System.out.println("프록시 끝");
    }
}
