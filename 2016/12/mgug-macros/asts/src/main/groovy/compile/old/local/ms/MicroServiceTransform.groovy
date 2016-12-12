package compile.old.local.ms

import static org.codehaus.groovy.ast.tools.GeneralUtils.args
import static org.codehaus.groovy.ast.tools.GeneralUtils.stmt
import static org.codehaus.groovy.ast.tools.GeneralUtils.param
import static org.codehaus.groovy.ast.tools.GeneralUtils.callX
import static org.codehaus.groovy.ast.tools.GeneralUtils.param
import static org.codehaus.groovy.ast.tools.GeneralUtils.block
import static org.codehaus.groovy.ast.tools.GeneralUtils.constX
import static org.codehaus.groovy.ast.tools.GeneralUtils.closureX

import org.codehaus.groovy.transform.AbstractASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.expr.ClosureExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.ast.stmt.ExpressionStatement

import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.classgen.VariableScopeVisitor

import groovy.transform.CompileStatic

import java.util.regex.Matcher

import spark.Spark
import spark.Request
import spark.Response

/**
 * Local transformation that converts a given class to a tiny
 * microservice.
 *
 * @since 0.1.0
 */
@CompileStatic
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class MicroServiceTransform extends AbstractASTTransformation {

    @Override
    void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        ClassNode microService = (ClassNode) nodes[1]
        List<MethodNode> methods = microService.methods

        addBehavior(microService, Json)
        addBehavior(microService, Csv)

        addMain(
            microService,
            methods.collect(this.&toSparkJava) as List<Statement>)

        removeMethods(microService, methods)
        reviewScopes(microService, sourceUnit)
    }

    void reviewScopes(ClassNode service, SourceUnit sourceUnit) {
        new VariableScopeVisitor(sourceUnit).visitClass(service)
    }

    void removeMethods(ClassNode service, List<MethodNode> methods) {
        methods.each { MethodNode method -> service.removeMethod(method) }
    }

    void addBehavior(ClassNode service, Class traitClass) {
       service.addInterface(ClassHelper.make(traitClass))
    }

    Statement toSparkJava(MethodNode methodNode) {
        String uri = methodNode.name
        Matcher matcher = uri =~ /\b(GET|POST|DELETE|PUT|PATCH)\b(.*)/
        List<String> uriFragments = matcher
          .findAll()
          .flatten()
          .takeRight(2) as List<String>

        ClosureExpression closure = closureX(sparkRouteParameters, methodNode.code)
        StaticMethodCallExpression methodCall = callX(
            ClassHelper.make(Spark),
            uriFragments.find().toLowerCase(),
            args(constX(uriFragments.last()), closure))

        return stmt(methodCall)
    }

    Parameter[] getSparkRouteParameters() {
        return [
            param(ClassHelper.make(Request), 'request'),
            param(ClassHelper.make(Response), 'response')
        ] as Parameter[]
    }

    void addMain(ClassNode classNode, List<Statement> sparkJavaCalls) {
        Parameter args = param(ClassHelper.make(String[]), 'args')
        MethodNode methodNode =
            new MethodNode(
                'main',
                ACC_PUBLIC | ACC_STATIC,
                ClassHelper.VOID_TYPE,
                [args] as Parameter[],
                [] as ClassNode[],
                block(sparkJavaCalls as Statement[]) as Statement)

        classNode.addMethod(methodNode)
    }
}
