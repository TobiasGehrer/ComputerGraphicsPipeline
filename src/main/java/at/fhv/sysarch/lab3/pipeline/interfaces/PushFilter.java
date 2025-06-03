package at.fhv.sysarch.lab3.pipeline.interfaces;

public interface PushFilter<TIn, TOut> extends PushPipe<TIn> {
    void setTarget(PushPipe<? super TOut> target);
}
