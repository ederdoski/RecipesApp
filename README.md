# Informacion General

Arquitectura: 

Para este proyecto decidí usar la arquitectura MVVM (Model-View-ViewModel) con una arquitectura reactiva. Esta arquitectura es ideal para proyectos Android ya que nos permite separar la lógica de nuestra aplicación de la interfaz de usuario, lo que hace que nuestra aplicación sea más sencilla de mantener y escalar.

Por otro lado decidí usar las últimas novedades de la plataforma Android, como la nueva API de LiveData y ViewModel, para garantizar que la aplicación sea escalable y robusta. 

Patrones de diseño : 

Decidí usar el patrón de diseño de Factory constructores para asegurarme de que todos los objetos se crean de la misma manera. 
Utilizando una biblioteca de inyeccion de dependencia llamada koin, me encargue de evitar problemas las famosas fugas de memoria y respectar el patron singleton de esta forma puedo asegurarme de que todos los objetos se crean una sola vez y que estan en mejor control que haciendo el proceso manualmente, la razon por la que use Koin como inyector de dependencia es porque esta hecho 100% en kotlin y es bastante ordenado de usar.

Api : 
Decidí usar Retrofit para conectarme a un repositorio gratuiro de resetas llamado Tasty, elegi esta librerías porque es la más utilizadas para conectarse a APIs.

Componentes : 

* Para mostrar los videos, usé el componente VideoView nativo de Android, dado que no debia hacer muchas operaciones con el mismo, de otro modo hubiera usado algo mas robusto como Exoplayer.

* Trabaje con el componente de navegación de Android para moverme entre los fragmentos de la aplicación.

* Usé la biblioteca de Google Maps para mostrar la ubicación de la receta.

Bibliotecas adicionales utilizadas : 

SDP Text Responsive: es una biblioteca que permite adaptar automaticamente los textos seguns las diferentes densidades de pantalla 
    
Glide: Utilizado para cargar las imagenes.


