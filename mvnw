#!/bin/sh
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

WRAPPER_VERSION="3.2.0"
MAVEN_WRAPPER_DOWNLOAD_URL="https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
MAVEN_WRAPPER_JAR_PATH="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES_PATH="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

# OS specific support.
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  MINGW* )
    mingw=true;;
  Darwin* )
    darwin=true
    ;;
esac

basedir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-"$basedir"}

if [ ! -d "$MAVEN_PROJECTBASEDIR/.mvn/wrapper" ]; then
  mkdir -p "$MAVEN_PROJECTBASEDIR/.mvn/wrapper"
fi

if [ ! -f "$MAVEN_WRAPPER_JAR_PATH" ]; then
  echo "- Downloading from: $MAVEN_WRAPPER_DOWNLOAD_URL"
  
  if command -v wget > /dev/null; then
    echo "Found wget ... using wget"
    wget -O "$MAVEN_WRAPPER_JAR_PATH" "$MAVEN_WRAPPER_DOWNLOAD_URL" || rm -f "$MAVEN_WRAPPER_JAR_PATH"
  elif command -v curl > /dev/null; then
    echo "Found curl ... using curl"
    curl -Lo "$MAVEN_WRAPPER_JAR_PATH" "$MAVEN_WRAPPER_DOWNLOAD_URL" || rm -f "$MAVEN_WRAPPER_JAR_PATH"
  else
    echo "- Error: JAVA_HOME is not set and no 'java' command could be found in your PATH."
    echo ""
    echo "Please set the JAVA_HOME variable in your environment to match the"
    echo "location of your Java installation."
    exit 1
  fi
fi

if [ ! -f "$MAVEN_WRAPPER_PROPERTIES_PATH" ]; then
  echo "distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip" > "$MAVEN_WRAPPER_PROPERTIES_PATH"
fi

exec java -cp "$MAVEN_WRAPPER_JAR_PATH" org.apache.maven.wrapper.MavenWrapperMain "$@"
