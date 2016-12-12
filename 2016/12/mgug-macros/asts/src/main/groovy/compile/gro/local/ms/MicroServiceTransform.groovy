package compile.gro.local.ms

import static org.codehaus.groovy.ast.tools.GeneralUtils.*
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*

import org.codehaus.groovy.control.*
import org.codehaus.groovy.classgen.*

import asteroid.A
import asteroid.Phase
import asteroid.AbstractLocalTransformation

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
@Phase(Phase.LOCAL.SEMANTIC_ANALYSIS)
class MicroServiceTransform extends AbstractLocalTransformation<MicroService, ClassNode> {

    @Override
    void doVisit(AnnotationNode annotation, ClassNode microService) {
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
        Parameter args = param(ClassHelper.make(String), 'args')
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
