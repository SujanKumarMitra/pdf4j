#!/bin/bash

JAVA_17_HOME="/Library/Java/JavaVirtualMachines/amazon-corretto-17.jdk/Contents/Home"

JAR_NAME="pdf4j-standalone.jar"
JAR_FOLDER="$(dirname "$(realpath "$0")")/build/libs"

CMD_TO_RUN="${JAVA_17_HOME}/bin/java -jar ${JAR_FOLDER}/${JAR_NAME}"

echo "Executing ${CMD_TO_RUN} $@"
exec ${CMD_TO_RUN} "$@"
