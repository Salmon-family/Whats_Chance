//
// Created by Falah on 09/11/2022.
//

#include <jni.h>
#include <string>
extern "C"
JNIEXPORT jstring JNICALL
Java_com_thechance_whatschance_domain_Keys_secretKey(JNIEnv *env, jobject thiz) {
    std::string secretKey = "a123edfr54321qas";
    return env->NewStringUTF(secretKey.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_thechance_whatschance_domain_Keys_secretIv(JNIEnv *env, jobject thiz) {
    std::string secretIv = "acxs3%y^&u*)smae";
    return env->NewStringUTF(secretIv.c_str());
}