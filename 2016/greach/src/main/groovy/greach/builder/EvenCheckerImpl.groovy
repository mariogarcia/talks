package greach.builder

import static org.codehaus.groovy.ast.tools.GeneralUtils.param

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.builder.AstBuilder

import asteroid.A
import asteroid.local.LocalTransformation
import asteroid.local.LocalTransformationImpl

@LocalTransformation(A.PHASE_LOCAL.INSTRUCTION_SELECTION)
class EvenCheckerImpl extends LocalTransformationImpl<EvenChecker,ClassNode> {

    @Override
    void doVisit(AnnotationNode annotationNode, ClassNode classNode, SourceUnit sourceUnit) {
        // tag::classHelper[]
        MethodNode method = A.NODES.method('checkEven')
            .returnType(Boolean) // passing a class
            .modifiers(A.ACC.ACC_PUBLIC)
            .parameters(param(ClassHelper.make(Number), "number")) // passing a class node
            .code(blockStmt)
            .build()

        classNode.addMethod(method)
        // tag::classHelper[]
    }

    // tag::buildFromCode[]
    BlockStatement getBlockStmt() {
        ASTNode[] stmts = new AstBuilder().buildFromCode {
            return number % 2 == 0
        }

        return stmts.first() as BlockStatement
    }
    // end::buildFromCode[]
}
