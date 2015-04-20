#!/bin/bash

EXITED_ARTIFACTORY_COUNT=$(docker ps --filter=status=exited -a | grep artifactory | wc -l)
if [ "$EXITED_ARTIFACTORY_COUNT" != "0" ]
then
  echo "Removing Old Artifactory"
  docker ps --filter=status=exited -a -q | xargs docker rm
fi

ARTIFACTORY_COUNT=$(docker ps --filter=status=running | grep artifactory | wc -l)
if [ "${ARTIFACTORY_COUNT}" != "1" ]
then
  echo "Starting Artifactory"
  mkdir -p /data/artifactory/data /data/logs/artifactory
  docker run --name "artifactorydata" -v /data/artifactory/data:/opt/artifactory/data -v /data/logs/artifactory:/opt/artifactory/logs tianon/true
  docker run -d -p 8081:8081 --name "artifactory" --volumes-from artifactorydata  codingtony/artifactory
fi

