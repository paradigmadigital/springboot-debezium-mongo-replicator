#!/bin/bash
set -e

stopEnvironment(){
  docker-compose -f docker/docker-compose.yml stop
  docker-compose -f docker/docker-compose.yml rm -f
}

stopEnvironment