package greach.local

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.*
// tag::import[]
import static org.codehaus.groovy.ast.tools.GeneralUtils.*
// end::import[]
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@CompileStatic
// tag::classdef[]
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class WithLoggingGETransformation implements ASTTransformation {
// end::classdef[]

    // tag::visit[]
    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        MethodNode method = (MethodNode) nodes[1]

        def startMessage = createPrintlnAst("Starting $method.name")
        def endMessage = createPrintlnAst("Ending $method.name")

        def existingStatements = ((BlockStatement)method.code).statements
        existingStatements.add(0, startMessage)
        existingStatements.add(endMessage)

    }
    // end::visit[]

    // tag::expressionstatement[]
    private static Statement createPrintlnAst(String message) {
        return stmt(callThisX("println", args(constX(message))))
    }
    // end::expressionstatement[]
}
