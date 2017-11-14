#!/usr/bin/env bash

if ! -e `which sqlite3` >/dev/null; then
	apt install sqlite3
fi

cd ../data

[[ !-e random_numbers.txt  && -e random_numbers.txt.tar.gz ]] && tar xvfz random_numbers.txt.tar.gz || exit 1

export DATABASE=random_numbers.db
export UID=`date -u | tr ' ' '_'`

if [[ -e $DATABASE ]] >/dev/null; then
   mv $DATABASE $DATABASE_$UID
fi

export HERE_DOC=<<-EOF
create TABLE input_file (numbers INTEGER)
.import random_numbers.txt numbers
.timer on
select * from input_file LIMIT 10
select * from input_file order by numbers ASC LIMIT 10
create index sorting on input_file(numbers)
select * from input_file order by numbers ASC LIMIT 10
EOF

echo $HERE_DOC

#sqlite3 -line $DATABASE $HERE_DOC
