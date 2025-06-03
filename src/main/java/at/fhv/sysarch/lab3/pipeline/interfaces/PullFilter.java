package at.fhv.sysarch.lab3.pipeline.interfaces;

public interface PullFilter<TIn, TOut> extends PullPipe<TOut> {
    void setSource(PullPipe<TIn> source);
}
