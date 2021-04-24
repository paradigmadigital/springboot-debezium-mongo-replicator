#!/bin/bash
set -e

BASEDIR=$(dirname "$0")

stopEnvironment(){
  docker-compose -f "$BASEDIR"/docker/docker-compose.yml stop
  docker-compose -f "$BASEDIR"/docker/docker-compose.yml rm -f
}

stopEnvironment