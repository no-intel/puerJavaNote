package proxy.statics;

import java.util.Random;

public class ServiceImpl implements Service{
    @Override
    public void save(){
        this.userSave();
        this.itemSave();
    }

    private void userSave(){
        int random = new Random().nextInt(3);
        if (random == 2){
            System.out.println("유저 random 번호 : " + random);
            throw new RuntimeException("유저 저장 실패");
        }
        System.out.println("유저 저장. random 번호 : " + random);
    }

    private void itemSave(){
        int random = new Random().nextInt(3);
        if (random == 2){
            System.out.println("아이템 random 번호 : " + random);
            throw new RuntimeException("아이템 저장 실패");
        }
        System.out.println("아이템 저장. random 번호 : " + random);
    }
}
