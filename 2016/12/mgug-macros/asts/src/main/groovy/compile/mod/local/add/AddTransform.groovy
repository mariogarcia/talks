package compile.mod.local.add

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*

import org.codehaus.groovy.control.*
import org.codehaus.groovy.transform.*

@groovy.transform.CompileStatic
@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class AddMethodASTTransformation extends AbstractASTTransformation {
    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        // tag::macroSimple[]
        ClassNode classNode = (ClassNode) nodes[1]

        ReturnStatement code = macro { return "42" }

        MethodNode methodNode =
            new MethodNode(
                "getMessage",
                ACC_PUBLIC,
                ClassHelper.make(String),
                [] as Parameter[],
                [] as ClassNode[],
                code)

        classNode.addMethod(methodNode)
        // end::macroSimple[]
    }
}
