package madridgug.macros.method

import static org.codehaus.groovy.ast.tools.GeneralUtils.*

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.macro.transform.MacroClass
import org.codehaus.groovy.ast.ClassHelper

@CompileStatic
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class ToMD5Transformation extends AbstractASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        FieldNode fieldNode = (FieldNode) nodes[1]
        MethodNode md5Method = getMD5Method(fieldNode.name)

        fieldNode.declaringClass.addMethod(md5Method)
    }

    MethodNode getMD5Method(final String propertyName) {
        BlockStatement stmt = getMD5Code(propertyName)
        MethodNode methodNode = new MethodNode(
            "${propertyName}ToMD5",
            ACC_PUBLIC,
            ClassHelper.make(String),
            [] as Parameter[],
            [] as ClassNode[],
            stmt
        )

        return methodNode
    }

    // tag::longer_use_case[]
    BlockStatement getMD5Code(final String propertyName) {
        return macro(true) {
            java.security.MessageDigest.getInstance('MD5')
                .digest(${propertyName}.getBytes('UTF-8'))
                .encodeHex()
                .toString()
        }
    }
    // end::longer_use_case[]
}
