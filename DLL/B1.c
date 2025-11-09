#include <stdio.h>
#include "B1.h"

JNIEXPORT int JNICALL Java_B1_add(JNIEnv *env, jobject javaobj, jint num1, jint num2) {
    return num1 + num2;
}
 

JNIEXPORT int JNICALL Java_B1_sub(JNIEnv *env, jobject javaobj, jint num1, jint num2) {
    return num1 - num2;
}
 
JNIEXPORT int JNICALL Java_B1_mult(JNIEnv *env, jobject javaobj, jint num1, jint num2) {
    return num1 * num2;
}
 
JNIEXPORT dou JNICALL Java_B1_div(JNIEnv *env, jobject javaobj, jint num1, jint num2) {
    return num1 / num2;
}
 
