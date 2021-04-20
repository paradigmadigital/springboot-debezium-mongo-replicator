#!/bin/bash

set -e

initEnvironment(){
  printf "\nStarting environment...\n"
  docker-compose -f docker/docker-compose.yml up -d
}

registerConnector(){
  printf "\nRegistering Debezium connector...\n"
  sleep 20
  curl -s -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d @debezium/connector-mysql.json > /dev/null
}

initEnvironment
registerConnector