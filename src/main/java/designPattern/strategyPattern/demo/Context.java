package designPattern.strategyPattern.demo;

public class Context {

    private Strategy strategy;

    //初始化时传入的是实现Strategy的A或B或C
    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    //根据具体的策略对象，调用不同的算法
    public void excuteAlgorithm(){
        strategy.algorithmInterface();
    }

    public static void main(String[] args) {
        Context context;

        //由于实例化了不同的策略，所以此时输出也不同
        context = new Context(new StrategyImplA());
        context.excuteAlgorithm();
        context = new Context(new StrategyImplB());
        context.excuteAlgorithm();
        context = new Context(new StrategyImplC());
        context.excuteAlgorithm();
    }
}
