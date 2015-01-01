#!/bin/bash

# match three digits
export DDD="\([0-9][0-9][0-9]\)"

echo "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>"
echo "<html><head>"
echo "<style>"
echo "pre {"
echo "  white-space: pre-wrap;"
echo "}"
echo "</style>"
echo "</head><body>"
echo "<h3>Missing Pokemon Cards</h3>"

echo "<pre>"
while read line; do
  SET_NUMBER=$(echo $line | cut -f1 -d',')
  SET_NUMBER=$(printf "%03d" ${SET_NUMBER})
  SET_NAME=$(echo $line | cut -f2 -d',')
  SET_FILENAME=$(echo $line | cut -f3 -d',')
  SET_COUNT=$(echo $line | cut -f4 -d',')
  if [ "$SET_NUMBER" != "063" ]
  #if [ "$SET_NUMBER" == "050" ]
  then
    FILE="${SET_NUMBER}.${SET_FILENAME}.set"
    INVENTORY="inventory/${FILE}"
    FULL_SET="full_sets/${FILE}"
    SET_COUNT=$(wc -l $FULL_SET | cut -f1 -d' ')
    D=$(cat inventory/${FILE} | diff full_sets/${FILE} - | grep "<" | cut -f2 -d' ' | sed "s^$DD ^<b>\1</b> ^g" | sed "s^$DD$^<b>\1</b>^g")
    #D=$(cat inventory/${FILE} | cut -f1 -d' ' | diff full_sets/${FILE} - | grep "<" | cut -f2 -d' ')
    MISSING_CARD_COUNT=$(echo "$D" | wc -l)
    CARD_COUNT=$(($SET_COUNT - $MISSING_CARD_COUNT))
    PERCENT=$(echo "scale=1; ($CARD_COUNT / $SET_COUNT) * 100" | bc)
    echo "<br/>" 
    echo "<img src='images/${SET_NUMBER}.png' height='20' width='20'/> &nbsp; &nbsp; ${SET_NUMBER}: ${SET_NAME} - Missing[${MISSING_CARD_COUNT}] - Percent Complete[${PERCENT}%]<br/>"
    echo $D
  fi
done <sets.txt

echo "</pre>"
