#!/usr/bin/bash
#set -x

rm -f *.i

# =========================================================
lins=(5 10 20 30 50 70 100 150 200 250)
file='tamanho'

	echo $file >> $file.i
for i in ${lins[@]}; do
	echo $i >> $file.i
done

# =========================================================
lins=(3 5 10 15 25 35 50 75 100 125)
file='unary_add'

	echo $file >> $file.i
for i in ${lins[@]}; do
	myvar=`perl -e "print '1' x $i;"`
	echo -ne $i '\t' $myvar+$myvar= >> $file.i
	echo >> $file.i
done

# =========================================================
lins=(5 10 20 30 50 70 100 150 200 250)
file='palindrome'

	echo $file >> $file.i
for i in ${lins[@]}; do
	myvar=`perl -e "print '1' x $i;"`
	echo -ne $i '\t' $myvar >> $file.i
	echo >> $file.i
done


# =========================================================
lins=(3 5 10 15 25 35 50 75 100 125)
file='0n1n'

	echo $file >> $file.i
for i in ${lins[@]}; do
	ones=`perl -e "print '1' x $i;"`
	zeros=`perl -e "print '0' x $i;"`
	echo -ne $i '\t' $zeros$ones >> $file.i
	echo >> $file.i
done


# =========================================================
lins=(6 10 20 30 50 70 100 150 200 250)
file='02n'

	echo $file >> $file.i
for i in ${lins[@]}; do
	myvar=`perl -e "print '0' x $i;"`
	echo -ne $i '\t' $myvar >> $file.i
	echo >> $file.i
done
