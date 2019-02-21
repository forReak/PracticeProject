package reflection;

public class Cat extends Animal {

    private int foots = 4;

    public boolean canJump = true;

    private void sayMiao(){
        System.out.println("miao~~");
    }

    public void jump(){
        System.out.println("cat is jumping");
    }
}
