# **Challenge Instaleap**


![](app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp)


***Tabla de contenido***

1. [Descripción](#descripción)
2. [Intalación y uso](#intalación-y-uso)
3. [Desarrollo](#desarrollo)

## **Descripción**

En esta app puedes visualizar peliculas y series de tv, además puedes visualizar las diferentes categorías que existen y las películas o series de la categoría escogida. Tambien puedes ir al detalle y revisar cuando dura la película o cuantas temporadas tiene la serie, además de tener su descripción, entre otros.


## **Intalación y uso**

*Prerequisitos:*

- Android Studio (^4.2.1)
- Kotin (^1.8.10)

*Herramientas:*

- SDK > 24
- CompileSdk 33
- Compose 1.2.0
- Navigation 2.5.3
- Hilt 2.44
- Retrofit 2.9.0


## **Desarrollo**

- Para implementar una libreria se debe colocar la verión en el archivo **version.gradle** ubicado en la carpeta buldsystem en la raiz del proyecto.
- Colocar las imagenes en SVGs
- Todo esta realizado en Jetpack Compose


### *Arquitectura*

Esta app usa la [***Clean Architecture***](https://developer.android.com/topic/architecture?hl=es-419#recommended-app-arch) integrada con [***MVVM Android***](https://www.journaldev.com/20292/android-mvvm-design-pattern).

![](https://media.geeksforgeeks.org/wp-content/uploads/20201002215007/MVVMSchema.png)

### *Patrones de diseño*

- Creacionales
    - Factory
    - Singleton
    - Builder

- Estructurales
    - Facade
    - Proxy
    - Repository

- Comportamientos
    - Chain of resposability
    - Mediator
    - Observer
    - State