/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_amd_aparapi_OpenCLRunner */

#ifndef _Included_com_amd_aparapi_OpenCLRunner
#define _Included_com_amd_aparapi_OpenCLRunner
#ifdef __cplusplus
extern "C" {
#endif
#undef com_amd_aparapi_OpenCLRunner_ARG_BOOLEAN
#define com_amd_aparapi_OpenCLRunner_ARG_BOOLEAN 1L
#undef com_amd_aparapi_OpenCLRunner_ARG_BYTE
#define com_amd_aparapi_OpenCLRunner_ARG_BYTE 2L
#undef com_amd_aparapi_OpenCLRunner_ARG_FLOAT
#define com_amd_aparapi_OpenCLRunner_ARG_FLOAT 4L
#undef com_amd_aparapi_OpenCLRunner_ARG_INT
#define com_amd_aparapi_OpenCLRunner_ARG_INT 8L
#undef com_amd_aparapi_OpenCLRunner_ARG_DOUBLE
#define com_amd_aparapi_OpenCLRunner_ARG_DOUBLE 16L
#undef com_amd_aparapi_OpenCLRunner_ARG_LONG
#define com_amd_aparapi_OpenCLRunner_ARG_LONG 32L
#undef com_amd_aparapi_OpenCLRunner_ARG_SHORT
#define com_amd_aparapi_OpenCLRunner_ARG_SHORT 64L
#undef com_amd_aparapi_OpenCLRunner_ARG_ARRAY
#define com_amd_aparapi_OpenCLRunner_ARG_ARRAY 128L
#undef com_amd_aparapi_OpenCLRunner_ARG_PRIMITIVE
#define com_amd_aparapi_OpenCLRunner_ARG_PRIMITIVE 256L
#undef com_amd_aparapi_OpenCLRunner_ARG_READ
#define com_amd_aparapi_OpenCLRunner_ARG_READ 512L
#undef com_amd_aparapi_OpenCLRunner_ARG_WRITE
#define com_amd_aparapi_OpenCLRunner_ARG_WRITE 1024L
#undef com_amd_aparapi_OpenCLRunner_ARG_LOCAL
#define com_amd_aparapi_OpenCLRunner_ARG_LOCAL 2048L
#undef com_amd_aparapi_OpenCLRunner_ARG_GLOBAL
#define com_amd_aparapi_OpenCLRunner_ARG_GLOBAL 4096L
#undef com_amd_aparapi_OpenCLRunner_ARG_CONSTANT
#define com_amd_aparapi_OpenCLRunner_ARG_CONSTANT 8192L
#undef com_amd_aparapi_OpenCLRunner_ARG_ARRAYLENGTH
#define com_amd_aparapi_OpenCLRunner_ARG_ARRAYLENGTH 16384L
#undef com_amd_aparapi_OpenCLRunner_ARG_APARAPI_BUF
#define com_amd_aparapi_OpenCLRunner_ARG_APARAPI_BUF 32768L
#undef com_amd_aparapi_OpenCLRunner_ARG_EXPLICIT
#define com_amd_aparapi_OpenCLRunner_ARG_EXPLICIT 65536L
#undef com_amd_aparapi_OpenCLRunner_ARG_EXPLICIT_WRITE
#define com_amd_aparapi_OpenCLRunner_ARG_EXPLICIT_WRITE 131072L
#undef com_amd_aparapi_OpenCLRunner_ARG_OBJ_ARRAY_STRUCT
#define com_amd_aparapi_OpenCLRunner_ARG_OBJ_ARRAY_STRUCT 262144L
#undef com_amd_aparapi_OpenCLRunner_ARG_CHAR
#define com_amd_aparapi_OpenCLRunner_ARG_CHAR 2097152L
#undef com_amd_aparapi_OpenCLRunner_ARG_STATIC
#define com_amd_aparapi_OpenCLRunner_ARG_STATIC 4194304L
#undef com_amd_aparapi_OpenCLRunner_JNI_FLAG_USE_GPU
#define com_amd_aparapi_OpenCLRunner_JNI_FLAG_USE_GPU 4L
#undef com_amd_aparapi_OpenCLRunner_JNI_FLAG_LAMBDA_KERNEL
#define com_amd_aparapi_OpenCLRunner_JNI_FLAG_LAMBDA_KERNEL 8L
#undef com_amd_aparapi_OpenCLRunner_JNI_FLAG_CLASSIC_KERNEL
#define com_amd_aparapi_OpenCLRunner_JNI_FLAG_CLASSIC_KERNEL 16L
/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    initJNI
 * Signature: (Ljava/lang/Object;Lcom/amd/aparapi/OpenCLDevice;I)J
 */
JNIEXPORT jlong JNICALL Java_com_amd_aparapi_OpenCLRunner_initJNI
  (JNIEnv *, jclass, jobject, jobject, jint);

/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    setArgsJNI
 * Signature: (J[Lcom/amd/aparapi/OpenCLRunner/KernelArg;I)I
 */
JNIEXPORT jint JNICALL Java_com_amd_aparapi_OpenCLRunner_setArgsJNI
  (JNIEnv *, jobject, jlong, jobjectArray, jint);

/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    runJNI
 * Signature: (JLcom/amd/aparapi/Range;ZI)I
 */
JNIEXPORT jint JNICALL Java_com_amd_aparapi_OpenCLRunner_runJNI
  (JNIEnv *, jobject, jlong, jobject, jboolean, jint);

/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    buildProgramJNI
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_amd_aparapi_OpenCLRunner_buildProgramJNI
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    disposeJNI
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_amd_aparapi_OpenCLRunner_disposeJNI
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    getExtensionsJNI
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_amd_aparapi_OpenCLRunner_getExtensionsJNI
  (JNIEnv *, jobject, jlong);

/*
 * Class:     com_amd_aparapi_OpenCLRunner
 * Method:    getProfileInfoJNI
 * Signature: (J)Ljava/util/List;
 */
JNIEXPORT jobject JNICALL Java_com_amd_aparapi_OpenCLRunner_getProfileInfoJNI
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
