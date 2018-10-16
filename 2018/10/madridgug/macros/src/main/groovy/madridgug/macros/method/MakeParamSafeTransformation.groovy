package madridgug.macros.method

import static org.codehaus.groovy.ast.tools.GeneralUtils.*

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@CompileStatic
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class MakeParamSafeTransformation implements ASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        MethodNode methodNode = Utils.getAnnotatedNode(nodes)
        BlockStatement oldCode = Utils.getMethodNodeCode(methodNode)

        // tag::macro[]
        VariableExpression paramRef = Utils.getParamAsVarX(methodNode) // <1>

        IfStatement safeGuard = macro {
            if (! $v { paramRef }) { // <2>
                return 1
            }
        }
        // end::macro[]

        methodNode.setCode(block(safeGuard, oldCode))
    }

    private static IfStatement getIfStatement(String paramName) {
        // tag::longer[]
        IfStatement safeGuard = new IfStatement(
            new NotExpression(
                new VariableExpression(paramName)
            ),
            new ReturnStatement(new ConstantExpression(1)),
            new EmptyStatement()
        )
        // end::longer[]
        return safeGuard
    }

    private static IfStatement getIfStatementShorter(String paramName) {
        // tag::shorter[]
        IfStatement safeGuard = ifS(
            notX(varX(paramName)),
            returnS(constX(1))
        ) as IfStatement
        // end::shorter[]

        return safeGuard
    }
}
