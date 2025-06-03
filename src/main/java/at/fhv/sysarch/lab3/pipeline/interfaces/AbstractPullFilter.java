package at.fhv.sysarch.lab3.pipeline.interfaces;

import java.util.Optional;

public abstract class AbstractPullFilter<TIn, TOut> implements PullFilter<TIn, TOut> {
    protected PullPipe<TIn> source;

    @Override
    public void setSource(PullPipe<TIn> source) {
        this.source = source;
    }

    protected abstract Optional<TOut> process(TIn data);

    @Override
    public Optional<TOut> pull() {
        if (source == null) {
            return Optional.empty();
        }

        Optional<TIn> input = source.pull();
        if (input.isEmpty()) {
            return Optional.empty();
        }

        return process(input.get());
    }
}
