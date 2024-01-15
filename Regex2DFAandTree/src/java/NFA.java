package java;

public class NFA {
    State start;
    State accept;

    NFA(State start, State accept) {
        this.start = start;
        this.accept = accept;
    }
}
