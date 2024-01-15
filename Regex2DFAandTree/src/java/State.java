package java;

import java.util.HashSet;
import java.util.Set;

public class State {
    int label;
    Set<State> transitions;

    State(int label) {
        this.label = label;
        this.transitions = new HashSet<>();
    }

    void addTransition(State state) {
        transitions.add(state);
    }

    void addTransition(State state, char symbol) {
        transitions.add(state);
        // Additional logic for symbol transitions can be added here
    }

}
