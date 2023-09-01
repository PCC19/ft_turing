image_name = clojure:lein
container_name = clojure_container
cdir=/tmp
porta=35000

startc:
	docker run --rm --name $(container_name) -v $(PWD):$(cdir) -p $(porta):$(porta) $(image_name) tail -f > /dev/null &
	sleep 3

bash: startc
	docker exec -it $(container_name) /bin/bash 

repl: startc
	docker exec -it $(container_name) lein repl :start :port $(porta)

run: startc
	# fazer export f='[caminho_json] [string_da_fita]' ante do 'make run'
	docker exec -it $(container_name) lein run $f

stop:
	docker container stop $(container_name)
