# Lab04-ARSW

# PART I

- Integrate to the base project supplied the Beans developed in the previous exercise. Just copy the classes, NOT the configuration files. Rectify that the dependency injection scheme is correctly configured with the @Service and @Autowired annotations.


- Modify the persistence bean InMemoryBlueprintPersistence so that by default it is initialized with at least three other planes, and with two associated with the same author.

# Part II

* Post

Con el fin de que se pudiera ingresar un bluePrint por medio de una petcion Post a la aplicaicon se crea el sigueinte metodo:


![](img/media/Parte2PostCode.png)

Por medio del comando unix curl podemos ingresar un objeto Bluerpint por medio del formato JSON como se muestra en la sigueinte imagen.

![](img/media/Parte2PostCurl.png)

Dandonos los siguientes resultados 

![](img/media/Parte2PostResultCurl.png)
--
![](img/media/Parte2PostResultCurl2.png)


* Put 

Con el fin de que se pudiera actualizar puntos en un blueprint es necesario realizar un nuevo metodo

![](img/media/Parte2PutCode.png)

Ademas es necesario mandar por paramteros el nombre del autor lo mismo que el nombre de la pintura con el fin de actualizar los puntos
Cabe mecionar que el usuario debe existir y debe tener esa pintura para poder realizar este metodo.

![](img/media/Parte2PutCurl.png)

Antes el usuario jack en la pintura paintExmaple tenia punto 140,140 en el primero y luego paso a 150,150 Como podemos verlo en la sigueinte imagen

![](img/media/Parte2PutResultCurl.png)
--
![](img/media/Parte2PutResultCurl2.png)





# Part III

* What race conditions could occur? 

una condicion de carrera se da cunado se actualiza la fuente principal de guardado de los datos, el cual es el hashMap 
Que contiene todos los blueprint con su respectivo nombre y autor. dandonos certeza que si piorizamos esta problematica no 
deberia existir este evento de condicion de carrera por un recurso.

* What are the respective critical regions? 

la region critca se da cada vez que se realiza un llamado a este HashMap ya sea para consulta o para ingresar un nuevo elemento a la estructura de datos.
Para este caso casi en cada metodo toca sincronizar el hashmap con el fin de garantizar que no hallan condiciones de carrera por el recurso 

se puede observar en la siguiente imagen:

![](img/media/Parte3.png)

cabe resaltar que esta modificcion se debe realizar desde la calse InMemoryBluePrintsPersistence ya que esta es donde se enceuntra la estructura de datos. 
