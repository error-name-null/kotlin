/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

package kotlin.reflect

/**
 * Represents a callable entity, such as a function or a property.
 *
 * @param R return type of the callable.
 */
@AllowDifferentMembersInActual // New 'KAnnotatedElement` supertype is added compared to the expect declaration
public actual interface KCallable<out R> : KAnnotatedElement {
    /**
     * The name of this callable as it was declared in the source code.
     * If the callable has no name, a special invented name is created.
     * Nameless callables include:
     * - constructors have the name "<init>",
     * - property accessors: the getter for a property named "foo" will have the name "<get-foo>",
     *   the setter, similarly, will have the name "<set-foo>".
     */
    @kotlin.internal.IntrinsicConstEvaluation
    public actual val name: String

    /**
     * The type of values returned by this callable.
     */
    public val returnType: KType
}
