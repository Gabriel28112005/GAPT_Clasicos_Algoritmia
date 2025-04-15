# Clásicos de la algoritmia

### Definición general del proyecto

El programa, al ejecutarse, mostrará una ventana en la cual se le pedirá al usuario que elija uno de los siguientes tres juegos:
 - La Torre de Hanoi.
 - El problema de las "n" reinas.
 - El problema del caballo.

Una vez el usuario haya realizado su elección, el programa deberá de resolver el ejercicio de la manera más eficiente y correcta posible. Además, deberá de mostrar el total de soluciones posibles (en el juego de El problema de las "n" reinas"), el número total de movimientos hechos (en La Torre de Hanoi) o el recorrido de posiciones que debe seguirse (en El problema del caballo).

### Descripción de los juegos y parámetros a rellenar en los mismos
 - **La Torre de Hanoi**:
   - El objetivo del juego es mover una torre de discos, apilados desde el de mayor tamaño (la base) hasta el de menor tamaño (la cúspide), situados en la columna izquierda hasta la columna derecha siguiendo una serie de reglas:
     - Solo se puede mover un disco por cada movimiento.
     - No se pueden colocar discos más grandes encima de otros más pequeños.
   - El usuario ingresará el total de discos con los que se comienza el juego. Posteriormente, el programa resolverá, correctamente, el nivel paso a paso y con el menor número de movimientos necesarios. Esta cantidad total de movimiento se le mostrará al usuario al finalizar el ejercicio.
 
 - **El problema de las "n" reinas**:
   - El objetivo del juego es colocar "n" reinas en un tablero de ajedrez (de tamaño nxn) de tal forma que ninguna de ellas pueda atacar a otra con un solo movimiento. Esto significa que no pueden estar en la misma fila, columna o diagonal.
   - El usuario ingresará el tamaño del tablero, n, y en consecuencia el programa mostrará las posiciones de las reinas, tales que cumplan con el desafío, y el número total de posibles soluciones para el tamaño de tablero

 - **El problema del caballo**:
   - El objetivo del juego es mover un caballo en un tablero de ajedrez (de dimensión nxn) de tal forma que recorra todas las casillas de la tabla sin repetir ninguna. 
   - El usuario ingresará la fila y la columna desde donde partirá el caballo, siendo esa su posición de salida, y el tamaño del tablero, n. Posteriormente, el programa mostrará el recorrido a seguir paso a paso.
