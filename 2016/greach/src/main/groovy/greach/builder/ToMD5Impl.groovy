package greach.builder

import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.AnnotationNode

import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.CompilePhase

import groovy.transform.CompileStatic

import asteroid.A
import asteroid.local.LocalTransformation
import asteroid.local.LocalTransformationImpl

// tag::abstractions[]
@CompileStatic
@LocalTransformation(A.PHASE_LOCAL.INSTRUCTION_SELECTION)
class ToMD5Impl extends
    LocalTransformationImpl<ToMD5,FieldNode> {

    @Override
    void doVisit(AnnotationNode annotation,
                 FieldNode fieldNode,
                 SourceUnit sourceUnit) {
        MethodNode md5Method = getMD5Method(fieldNode.name)

        fieldNode.declaringClass.addMethod(md5Method)
    }
    // end::abstractions[]

    MethodNode getMD5Method(final String propertyName) {
        BlockStatement stmt = getMD5Code(propertyName)

        return A.NODES.method("${propertyName}ToMD5")
           .returnType(String)
           .modifiers(A.ACC.ACC_PUBLIC)
           .code(stmt)
           .build()
    }

    // tag::buildFromString[]
    BlockStatement getMD5Code(final String propertyName) {
        def blockStatement = new AstBuilder().buildFromString """
            java.security.MessageDigest.getInstance('MD5')
                .digest(${propertyName}.getBytes('UTF-8'))
                .encodeHex()
                .toString()
        """

        return blockStatement.first() as BlockStatement
    }
    // end::buildFromString[]

}
