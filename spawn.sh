#!/bin/sh
set -e
mvn -B --quiet package -Ddir=/tmp/redis-target
exec java -jar /tmp/redis-target/jredis.jar "$@"