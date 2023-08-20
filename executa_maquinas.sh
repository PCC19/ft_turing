#!/usr/bin/bash
#set -x

rm *.o

path='./machines/'
ext='.json'

arq=("unary_add2" "palindrome2" "0n1n" "02n")

for file in ${arq[@]}; do
    cut -f 2 <(tail -n +2 $file.i) > tmp
    echo $file > $file.o
    for linha in $(cat tmp); do
        echo "Executing: " $file " with: " $linha
        lein run $path$file$ext $linha | grep Step | tail -n 1 | cut -f 2 -d: | cut -f1 -d'(' | tr -s ' ' >> $file.o;
    done
done

rm tmp

echo "Generating final table ..."
paste -d ',' tamanho.i *.o > tabela.csv

