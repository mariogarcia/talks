package madridgug.macros.method

import static org.codehaus.groovy.ast.tools.GeneralUtils.*

import groovy.transform.CompileStatic
import groovy.transform.CompileDynamic
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
@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class StatisticsASTTransformation extends AbstractASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        ClassNode classNode = (ClassNode) nodes[1]
        ClassNode templateClass = buildTemplateClass(classNode)

        templateClass.methods.each { MethodNode node ->
            classNode.addMethod(node)
        }
    }

    // tag::macroClass[]
    @CompileDynamic
    ClassNode buildTemplateClass(ClassNode reference) {
        def methodCount = constX(reference.methods.size())
        def fieldCount = constX(reference.fields.size())

        return new MacroClass() {
            class Statistics {
                java.lang.Integer getMethodCount() {
                    return $v { methodCount }
                }

                java.lang.Integer getFieldCount() {
                    return $v { fieldCount }
                }
            }
        }
    }
    // end::macroClass[]
}
