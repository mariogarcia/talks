package greach.local

import org.codehaus.groovy.transform.GroovyASTTransformationClass
import java.lang.annotation.*

// <1>
@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
// <2>
@GroovyASTTransformationClass(
    ["greach.local.WithLoggingExplainedTransformation"])
@interface WithLoggingExplained { }
