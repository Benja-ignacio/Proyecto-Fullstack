### Explicacion de como funciona bcrypt

## ¿Que es Bcrypt?

Bcrypt es una funcion de hashing de contraseñas diseñadas para ser intencionalmente lenta y computacionalmente intensiva, lo que ayuda a proteger contra ataques de fuerza bruta y tablas arcoíris (rainbow tables).

BCrypt utiliza automáticamente un valor aleatorio llamado salt, permitiendo que una misma contraseña genere hashes distintos.

Esta basado en el algoritmo de cifrado Blowfish y usa una variante llamada:

EksBlowfish(Expensive Key Schedule Blowfish)

# NOTA

BCrypt es un algoritmo de hashing, no de encriptación.

Las contraseñas no pueden desencriptarse.
Durante el login BCrypt vuelve a generar el hash y compara resultados.

# Como funciona?

El proceso simplificado de BCrypt es el siguiente:

1 - Se genera un salt aleatorio.
2 - BCrypt combina internamente la contraseña y el salt.
3 - Se ejecutan múltiples rondas del algoritmo de hashing según el cost factor.
4 - Se genera el hash final.

El hash resultante contiene:

- versión de BCrypt,
- cost factor,
- salt,
- hash final.

Ejemplo:

$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy

formato:

    $2a$10$[salt][hash]

$2a$ = version
10$ = cost factor
[salt] = salt generado por Bcrypt
[hash] = hash final generado

### Conceptos importantes

## Salt

El salt (sal) es un valor aleatorio generado automaticamente por BCrypt.

internamente tiene 16 bytes, que normalmente se representa como 22 caracteres

el salt evita que dos usuarios con la misma contraseña tenga el mismo hash.

ejemplo:

    password: hola123
    salt: ABC

resultado: hash(hola123 + ABC)

si el salt cambia:

    password: hola123
    salt: XYZ

el hash tambien sera distinto.

# Nota

en el ejemplo se uso ABC Y XYZ, cabe aclarar que son solo de ejemplo, el salt son 16 bytes aleatorios que bcrypt codifica a texto, que finalmente termina en un string de 22 caracteres.

## Cost factor

El cost factor determina qué tan costoso computacionalmente será generar el hash.

Cada incremento duplica aproximadamente el trabajo interno realizado por BCrypt.

Ejemplo:

cost = 10

2^10 = 1024

rondas internas aproximadas.

mientras mas rondas internas realiza BCrypt, más tiempo y CPU se necesitan para generar o verificar el hash, por esta razon al requerir más tiempo y recursos computacionales, los ataques de fuerza bruta se vuelven mucho más costosos y lentos.

Entonces el cost factor es básicamente:

    “qué tan lento quiero que sea BCrypt”

# NOTA

El cost factor NO cambia:

- el formato,
- el tamaño,
- ni la lógica del hash.

El cost factor SÍ cambia:

- cuánto CPU consume,
- cuánto tarda,
- y qué tan caro es atacar passwords.

## ¿Como funciona el login?

**BCrypt no desencripta contraseñas**

Durante el login:

1 - El usuario ingresa su contraseña.
2 - BCrypt extrae el salt y el cost factor desde el hash guardado.
3 - Se vuelve a generar el hash usando la contraseña ingresada, el mismo salt y el mismo cost factor.
4 - Si ambos hashes coinciden, la contraseña es válida.

# Ejemplo visual

Contraseña: hola123
Salt: ABC

hash(hola123 + ABC)
↓
ZZZ999 <-- hash generado

Hash almacenado: [cost][salt][hash]
↓
10ABCZZZ999

BCrypt no concatena literalmente strings como "hola123ABC".

El ejemplo es una simplificación conceptual para
mostrar que el salt participa en el proceso de hashing.
Internamente BCrypt utiliza EksBlowfish para procesar la contraseña, el salt y el cost factor.

// recordatorio: crear flujo usando java
