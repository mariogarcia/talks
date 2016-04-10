package greach.first

import groovy.transform.CompileStatic

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement

import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * taken from http://groovy-lang.org/metaprogramming.html#transforms-local
 */
@CompileStatic
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingTransformation implements ASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        MethodNode method = (MethodNode) nodes[1]

        def startMessage = createPrintlnAst("Starting $method.name")
        def endMessage = createPrintlnAst("Ending $method.name")

        def existingStatements = ((BlockStatement)method.code).statements
        existingStatements.add(0, startMessage)
        existingStatements.add(endMessage)

    }

    private static Statement createPrintlnAst(String message) {
        new ExpressionStatement(
            new MethodCallExpression(
                new VariableExpression("this"),
                new ConstantExpression("println"),
                new ArgumentListExpression(
                    new ConstantExpression(message)
                )
            )
        )
    }
}
