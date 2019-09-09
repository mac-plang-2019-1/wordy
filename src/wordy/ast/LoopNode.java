package wordy.ast;

import java.util.Map;
import java.util.Objects;

import wordy.interpreter.EvaluationContext;
import wordy.interpreter.LoopExited;

public class LoopNode extends StatementNode {
    public final StatementNode body;

    public LoopNode(StatementNode body) {
        this.body = body;
    }

    @Override
    public Map<String, ASTNode> getChildren() {
        return Map.of("body", body);
    }

    @Override
    public void run(EvaluationContext context) {
        context.trace(this);
        try {
            while(true)
                body.run(context);
        } catch(LoopExited e) {
            // OK!
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        LoopNode loopNode = (LoopNode) o;
        return body.equals(loopNode.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "LoopNode{body=" + body + '}';
    }
}
