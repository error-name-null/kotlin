/*
 * Copyright 2010-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the LICENSE file.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    apply(from = "$rootDir/kotlin-native/gradle/kotlinGradlePlugin.gradle")
}

plugins {
    kotlin("jvm")
}

tasks.named<KotlinCompile>("compileKotlin") {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xskip-metadata-version-check",
//            "-Xllvm-variant=/home/monkey/SourceCode/kotlin/kotlin-native/tools/llvm_builder/llvm-distribution",
        )

    }
}

dependencies {
    api(project(":kotlin-stdlib"))
    implementation(project(":compiler:util"))
}
