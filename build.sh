#!/bin/sh

SCRIPT_PATH=$(readlink -f "$0")
ROOT_PATH=$(dirname "$SCRIPT_PATH")

LIB_PATH="$ROOT_PATH/lib"

cd $ROOT_PATH

echo
echo "Removing server.jar and client.jar"
echo

rm server.jar
rm client.jar

echo
echo "Starting build..."
echo

javac -d . -cp ".:$ROOT_PATH/src:$LIB_PATH/kryo-4.0.1.jar:$LIB_PATH/kryonet-2.22.0-RC1.jar:$LIB_PATH/jfreechart-1.0.13.jar:$LIB_PATH/jcommon-1.0.16.jar:$LIB_PATH/minlog-1.3.0.jar:$LIB_PATH/objenesis-2.1.jar:$LIB_PATH/reflectasm-1.10.1-shaded.jar" $ROOT_PATH/src/server/ServerMain.java

jar cfm server.jar ./manifest/server_manifest.txt lib src server network util
rm -rf server

javac -d . -cp ".:$ROOT_PATH/src:$LIB_PATH/kryo-4.0.1.jar:$LIB_PATH/kryonet-2.22.0-RC1.jar:$LIB_PATH/jfreechart-1.0.13.jar:$LIB_PATH/jcommon-1.0.16.jar:$LIB_PATH/minlog-1.3.0.jar:$LIB_PATH/objenesis-2.1.jar:$LIB_PATH/reflectasm-1.10.1-shaded.jar" $ROOT_PATH/src/client/ClientMain.java

jar cfm client.jar ./manifest/client_manifest.txt lib src client network util
rm -rf client
rm -rf network

echo
echo "Build Complete."
echo