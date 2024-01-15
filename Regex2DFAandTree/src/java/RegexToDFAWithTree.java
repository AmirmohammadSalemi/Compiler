package java;

import java.util.Stack;

public class RegexToDFAWithTree {
    private static int stateCount = 0;

    public static NFA regexToNFA(String regex) {
        Stack<NFA> nfaStack = new Stack<>();

        for (char c : regex.toCharArray()) {
            switch (c) {
                case '*':
                    NFA nfa = nfaStack.pop();
                    State newStart = new State(stateCount++);
                    State newAccept = new State(stateCount++);
                    newStart.addTransition(nfa.start);
                    newStart.addTransition(newAccept);
                    nfa.accept.addTransition(nfa.start);
                    nfa.accept.addTransition(newAccept);
                    nfaStack.push(new NFA(newStart, newAccept));
                    break;
                case '|':
                    NFA second = nfaStack.pop();
                    NFA first = nfaStack.pop();
                    State newStartAlt = new State(stateCount++);
                    State newAcceptAlt = new State(stateCount++);
                    newStartAlt.addTransition(first.start);
                    newStartAlt.addTransition(second.start);
                    first.accept.addTransition(newAcceptAlt);
                    second.accept.addTransition(newAcceptAlt);
                    nfaStack.push(new NFA(newStartAlt, newAcceptAlt));
                    break;
                default:
                    State start = new State(stateCount++);
                    State accept = new State(stateCount++);
                    start.addTransition(accept);
                    start.addTransition(accept, c);
                    nfaStack.push(new NFA(start, accept));
                    break;
            }
        }

        return nfaStack.pop();
    }

    public static TreeNode constructTreeFromDFA(NFA dfa) {
        TreeNode root = new TreeNode("DFA");
        constructTreeFromState(root, dfa.start);
        return root;
    }

    private static void constructTreeFromState(TreeNode parent, State state) {
        TreeNode currentStateNode = new TreeNode("State " + state.label);

        for (State transition : state.transitions) {
            TreeNode transitionNode = new TreeNode("Transition");
            constructTreeFromState(transitionNode, transition);
            currentStateNode.children.add(transitionNode);
        }

        parent.children.add(currentStateNode);
    }

    public static void main(String[] args) {
        String regex = "a*b|c";
        NFA nfa = regexToNFA(regex);

        // Construct a tree from the DFA
        TreeNode dfaTree = constructTreeFromDFA(nfa);
        // Print or analyze the tree structure
        printTree(dfaTree, 0);
    }

    private static void printTree(TreeNode node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.value);

        for (TreeNode child : node.children) {
            printTree(child, level + 1);
        }
    }
}
