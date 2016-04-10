package greach.local

import groovy.transform.CompileStatic

// tag::importnodes[]
import org.codehaus.groovy.ast.*
// end::importnodes[]

// tag::importexpressions[]
import org.codehaus.groovy.ast.expr.*
// end::importexpressions[]

// tag::importstatements[]
import org.codehaus.groovy.ast.stmt.*
// end::importstatements[]

// tag::importcontrol[]
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.CompilePhase
// end::importcontrol[]

// tag::importtransform[]
import org.codehaus.groovy.transform.*
// end::importtransform[]

@CompileStatic
// tag::classdef[]
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingExplainedTransformation implements ASTTransformation {
// end::classdef[]

    // tag::visit[]
    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        MethodNode method = (MethodNode) nodes[1] // <1>

        def startMessage = createPrintlnAst("Starting $method.name")
        def endMessage = createPrintlnAst("Ending $method.name")

        def existingStatements = ((BlockStatement)method.code).statements // <2>
        existingStatements.add(0, startMessage)
        existingStatements.add(endMessage)

    }
    // end::visit[]

    // tag::expressionstatement[]
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
    // end::expressionstatement[]
}
