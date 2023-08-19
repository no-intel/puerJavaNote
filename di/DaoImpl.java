package di;
@Component
public class DaoImpl implements Dao{
    public void select(){
        System.out.println("DAO SELECT");
    };
}
