package at.fhv.sysarch.lab3.pipeline.interfaces;

import java.util.Optional;

public interface PullPipe<T> {
    Optional<T> pull();
}
