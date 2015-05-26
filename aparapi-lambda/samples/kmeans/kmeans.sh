#!/bin/sh
. ../../env.sh
export JARS="${JARS}:kmeans.jar"

export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.useAgent=true"
export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.executionMode=${1}"
export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.enableVerboseJNI=false"
export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.enableShowGeneratedHSAIL=true"

java ${JVM_OPTS} -classpath ${JARS} com.amd.aparapi.sample.kmeans.MainHSAIL