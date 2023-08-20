#!/bin/bash
FILE=$1

rm {a..d}
jq '.transitions | to_entries[] | (.key + (.value[] | .key))' $FILE > a
jq '.transitions | to_entries | .[].value[] | flatten | @sh' $FILE > b

paste a b | tr "\t\"\'" "   " | tr -s " " > c

echo "stateDiagram-v2" > d
awk '{ print($1,"-->",$3":", "("$2") " "["$4" "$5"]") }' c >> d
cat d

rm {a..d}
#jq -r '.transitions | path(..) | map(tostring) | join("/")' unary.json

