package at.fhv.sysarch.lab3.pipeline.interfaces;

public abstract class AbstractPushFilter<TIn, TOut> implements PushFilter<TIn, TOut> {
    protected PushPipe<? super TOut> target;

    @Override
    public void setTarget(PushPipe<? super TOut> target) {
        this.target = target;
    }

    protected abstract TOut process(TIn data);

    @Override
    public void push(TIn data) {
        if (data != null) {
            TOut result = process(data);
            if (result != null && target != null) {
                target.push(result);
            }
        }
    }
}
