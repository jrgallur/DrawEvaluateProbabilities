Idea:
1 - Proceso para calcular estadísticas de cada tipo posible y guardarlas en BBDD hasta una fecha determinada
	- Reiniciar cálculos (puesto que el tiempo no parece ser un problema, mejor reiniciar cálculos desde 0 que recalcular lo que falta, ya que eso puede ser complicado)
2 - Proceso para generar sorteos a partir de las estadísticas guardadas y los últimos sorteos
	- Utilizar combinaciones entre los tipos de estadísticas. Por ejemplo: 10% aleatorio, 90% último sorteo; 10% aleatorio, 70% último sorteo, 20% "le toca" porque hace mucho que no sale
3 - Proceso que llama a 2 tantas veces como sea necesario para acertar al menos 3 números. Se repite durante varios sorteos distintos
	- La idea es ver tras, por ejemplo, 100 sorteos (100 repeticiones), cuál es la mejor combinación de estadísticas.

Posibles tipos de cálculos
- Números que más veces han salido a lo largo de la historia -> En algún sitio he leído algo que indicaba que era buena idea tener los que más veces han salido de los últimos 2 años
- Números que más veces han salido en los últimos n sorteos
- Números que menos veces han salido (totales y n sorteos)
- Números según su orden de aparición, siendo más probable cuanto más tiempo hace que no sale -> HigherWhenLastAppearanceIsOlder
- Aleatorio
- Combinaciones de apariciones:
    - Último sorteo
    - Dos últimos sorteos
    - etc
