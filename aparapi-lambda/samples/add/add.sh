#!/bin/sh
. ../../env.sh
export JARS="${JARS}:add.jar"

export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.useAgent=true"
export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.executionMode=${1}"
export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.enableVerboseJNI=false"
export JVM_OPTS="${JVM_OPTS} -Dcom.amd.aparapi.enableShowGeneratedHSAIL=true"


START=$(date +%s%N)
#java ${JVM_OPTS} -classpath ${JARS} com.amd.aparapi.sample.add.Main
 java ${JVM_OPTS} -classpath ${JARS} com.amd.aparapi.sample.add.HSAAdd
 
 END=$(date +%s%N)
DIFF=$(($END - $START))

echo "It took $DIFF nanoseconds"
