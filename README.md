## Explicación Función Recursiva
Vamos a basarnos en esta imagen para poder hacer el metodo de recursividad donde:
<div align="center"> 
  
![Alter](Img/table.png)

</div>
Ahora vamos a definir la función que retornará la cantidad de veces que se hacer el conteo, en este caso seria cuantas veces va hacer la resta, por lo tanto: 
```Kotlin
fun recursiveFunction(izquierda:Int, derecha:Int): Int{
    if(derecha == 0){
       return throw IllegalArgumentException("No se puede dividir por cero")
    }else if(!(izquierda < derecha
```
                           
### Imagenes
- Diagrama de Clases:


<div align="center"> 
  
![Alter](Img/class.png)

</div>

