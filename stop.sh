#!/bin/sh

# Capture output then echo it to add newline.
JSON=$(curl --silent -X POST localhost:8080/shutdown)
echo $JSON
