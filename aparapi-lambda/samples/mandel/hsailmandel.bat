setlocal 
call ../../env.bat

set JARS=%JARS%;mandel.jar
set JARS=%JARS%;../common/common.jar
set JVM_OPTS=%JVM_OPTS% -Dcom.amd.aparapi.dumpFlags=true 
set JVM_OPTS=%JVM_OPTS% -Dcom.amd.aparapi.executionMode=%1 
set JVM_OPTS=%JVM_OPTS% -Dcom.amd.aparapi.logLevel=FINE
set JVM_OPTS=%JVM_OPTS% -Dcom.amd.aparapi.enableShowGeneratedOpenCL=true 

java %JVM_OPTS% -classpath %JARS% com.amd.aparapi.sample.mandel.HSAILMandel
endlocal 


