package madridgug.macros.method

import static org.codehaus.groovy.ast.tools.GeneralUtils.*

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.expr.*

class Utils {

    static <U> U getAnnotatedNode(ASTNode[] nodes) {
        return (U) nodes[1]
    }

    static BlockStatement getMethodNodeCode(MethodNode node) {
        return (BlockStatement) node.code
    }

    static VariableExpression getParamAsVarX(MethodNode node) {
        return varX(node.parameters.first().name)
    }
}
