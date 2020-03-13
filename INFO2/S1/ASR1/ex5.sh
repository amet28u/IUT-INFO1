#!/bin/bash

if [ $1 = 1 ]
then
  cat fruits | grep -e ^[A-Z]
elif [ $1 = 2 ]
then
  cat fruits | grep -e ^[a-z]
else
  exit 1
fi
