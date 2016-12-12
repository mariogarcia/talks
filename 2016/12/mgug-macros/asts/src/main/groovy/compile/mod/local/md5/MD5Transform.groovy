package compile.mod.local.md5

// tag::imports[]
import static org.codehaus.groovy.ast.tools.GeneralUtils.*
import static org.codehaus.groovy.ast.ClassHelper.*

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*

import org.codehaus.groovy.control.*
import org.codehaus.groovy.transform.*
// end::imports[]

// tag::transformation[]
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class MD5Transform extends AbstractASTTransformation {
// end::transformation[]

    // tag::visit[]
    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        ClassNode currentClass = (ClassNode) nodes[1]
        currentClass.fields.each { FieldNode field ->
            currentClass.addMethod(createMD5Method(field))
        }
    }
    // end::visit[]

    // tag::createMD5[]
    MethodNode createMD5Method(FieldNode node) {
        return new MethodNode("${node.name}asMD5",
            ACC_PUBLIC,
            ClassHelper.STRING_TYPE,
            [] as Parameter[],
            [] as ClassNode[],
            createBlock(node))
    }
    // end::createMD5[]

    // tag::macroStatement[]
    BlockStatement createBlock(FieldNode node) {
        VariableExpression fieldVar = GeneralUtils.varX(fieldNode.name)

        return macro(CompilePhase.SEMANTIC_ANALYSIS, true) {
            return java.security.MessageDigest
                    .getInstance('MD5')
                    .digest($v { fieldVar} .getBytes())
                    .encodeHex()
                    .toString()
        }
    }
    // end::macroStatement[]
}
