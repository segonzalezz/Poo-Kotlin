fun recursiveFunction(izquierda:Int, derecha:Int): Int{
    if(derecha == 0){
       return throw IllegalArgumentException("No se puede dividir por cero")
    }else if(!(izquierda < derecha)){
         return recursiveFunction(izquierda-derecha, derecha) + 1
    }else{
        return 0
    }  
}

fun main() { 
    var izquierda:Int = 12
    var derecha:Int = 3
    val resultado =recursiveFunction(izquierda, derecha)
    println("El resultado de $izquierda / $derecha es: $resultado")
}