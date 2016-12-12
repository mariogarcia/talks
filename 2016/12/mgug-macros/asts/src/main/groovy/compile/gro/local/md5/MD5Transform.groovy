package compile.gro.local.md5

import asteroid.A
import asteroid.Phase
import asteroid.AbstractLocalTransformation

import groovy.transform.CompileStatic

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.control.*
import org.codehaus.groovy.transform.*
import org.codehaus.groovy.ast.tools.GeneralUtils

@CompileStatic
// tag::declaration[]
@Phase(Phase.LOCAL.SEMANTIC_ANALYSIS)
class MD5Transform extends AbstractLocalTransformation<MD5, ClassNode> {
// end::declaration[]

    // tag::dovisit[]
    @Override
    void doVisit(AnnotationNode annotation, ClassNode node) {
        node.fields.each { FieldNode field ->
            node.addMethod(createMD5Method(field))
        }
    }
    // end::dovisit[]

    // tag::createmethod[]
    MethodNode createMD5Method(FieldNode node) {
        return A.NODES.method("${node.name}ToMD5")
          .modifiers(A.ACC.ACC_PUBLIC)
          .returnType(String)
          .code(createBlock(node))
          .build()
    }
    // end::createmethod[]

    // tag::createblock[]
    BlockStatement createBlock(FieldNode node) {
        VariableExpression fieldVar = GeneralUtils.varX(node.name)

        return macro(CompilePhase.SEMANTIC_ANALYSIS, true) {
            return java.security.MessageDigest
              .getInstance('MD5')
              .digest($v { fieldVar }.getBytes())
              .encodeHex()
              .toString()
        }
    }
    // end::createblock[]
}
