
-------------SOFTWARE DESIGN--------------
----------------------------------------------------------------------------
        Integrante              Rol                      Login Unindes
----------------------------------------------------------------------------
    Alex Chacon           Lider del Grupo               av.chacon10
    Camilo Niño           Líder de Desarrollo           jc.nino11
    Fernanda Calderón     Líder de Requerimientos       mf.calderon
    Fernando Gutierrez    Líder de Integración          pf.gutierrez10
    Ernesto Nobman        Líder de Calidad              ef.nobmann10
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-mpfreelancer)
  - [Entidad Freelancer](#entidad-freelancer)
  - [Entidad ProjectSponsor](#entidad-projectsponsor)
  - [Entidad Project](#entidad-project)
  - [Entidad Skill](#entidad-skill)
  - [Entidad Category](#entidad-category)
  - [Entidad Education](#entidad-education)
  - [Entidad Status](#entidad-status)

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /MPFreelancer.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación MPFreelancer
### Entidad Freelancer
#### Estructura de objeto Freelancer
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    rate: '' /*Tipo Integer*/,
    bithday: '' /*Tipo Date*/,
    picture: '' /*Tipo String*/,
    titles: [] /*Colección de registros de Education*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/freelancers|Lista los registros de Freelancer (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Freelancer y el total de registros en la base de datos en el header X-Total-Count
**GET**|/freelancers/*:freelancersid*|Obtener los atributos de una instancia de Freelancer (READ)|**@PathParam freelancersid**: Identificador del registro||Atributos de la instancia de Freelancer
**POST**|/freelancers|Crear una nueva instancia de la entidad Freelancer (CREATE)||Atributos de la instancia de Freelancer a crear|Instancia de Freelancer creada, incluyendo su nuevo ID
**PUT**|/freelancers/*:freelancersid*|Actualiza una instancia de la entidad Freelancer (UPDATE)|**@PathParam freelancersid**: Identificador del registro|Objeto JSON de Freelancer|Instancia de Freelancer actualizada
**DELETE**|/freelancers/*:freelancersid*|Borra instancia de Freelancer en el servidor (DELETE)|**@PathParam freelancersid**: Identificador del registro||
**GET**|freelancers/*:freelancersid*/skills|Listar registros de skills (Skill) asociados a Freelancer|**@PathParam freelancersid**: Identificador de instancia de Freelancer||Colección de objetos JSON de skills(Skill)
**GET**|freelancers/*:freelancersid*/skills/*:skillsid*|Obtener un registro de skills (Skill) asociado a Freelancer|**@PathParam freelancersid**: Identificador de instancia de Freelancer<br><br>**@PathParam skillsid**: Identificador de instancia de Skill||Lista de registros de skills(Skill)
**POST**|freelancers/*:freelancersid*/skills/*:skillsid*|Asocia una instancia de Skill a una de Freelancer|**@PathParam freelancersid**: Identificador de instancia de Freelancer<br><br>**@PathParam skillsid**: Identificador de instancia de Skill|Registro de skills(Skill) para asociar a Freelancer|Instancia de skills(Skill) asociada a instancia de Freelancer
**PUT**|freelancers/*:freelancersid*/skills|Actualización de instancias de skills (Skill) asociadas a Freelancer|**@PathParam freelancersid**: Identificador de instancia de Freelancer|Colección de instancias de skills(Skill) a actualizar|Colección de instancias de skills(Skill) actualizados
**DELETE**|freelancers/*:freelancersid*/skills/*:skillsid*|Remueve asociación de instancias de skills (Skill) a Freelancer|**@PathParam freelancersid**: Identificador de instancia de Freelancer<br><br>**@PathParam skillsid**: Identificador de instancia de Skill|Colección de atributo `id` de skills(Skill) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad ProjectSponsor
#### Estructura de objeto ProjectSponsor
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    company: '' /*Tipo String*/,
    picture: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/projectSponsors|Lista los registros de ProjectSponsor (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de ProjectSponsor y el total de registros en la base de datos en el header X-Total-Count
**GET**|/projectSponsors/*:projectSponsorsid*|Obtener los atributos de una instancia de ProjectSponsor (READ)|**@PathParam projectSponsorsid**: Identificador del registro||Atributos de la instancia de ProjectSponsor
**POST**|/projectSponsors|Crear una nueva instancia de la entidad ProjectSponsor (CREATE)||Atributos de la instancia de ProjectSponsor a crear|Instancia de ProjectSponsor creada, incluyendo su nuevo ID
**PUT**|/projectSponsors/*:projectSponsorsid*|Actualiza una instancia de la entidad ProjectSponsor (UPDATE)|**@PathParam projectSponsorsid**: Identificador del registro|Objeto JSON de ProjectSponsor|Instancia de ProjectSponsor actualizada
**DELETE**|/projectSponsors/*:projectSponsorsid*|Borra instancia de ProjectSponsor en el servidor (DELETE)|**@PathParam projectSponsorsid**: Identificador del registro||
**GET**|projectSponsors/*:projectSponsorsid*/projects|Listar registros de projects (Project) asociados a ProjectSponsor|**@PathParam projectSponsorsid**: Identificador de instancia de ProjectSponsor||Colección de objetos JSON de projects(Project)
**GET**|projectSponsors/*:projectSponsorsid*/projects/*:projectsid*|Obtener un registro de projects (Project) asociado a ProjectSponsor|**@PathParam projectSponsorsid**: Identificador de instancia de ProjectSponsor<br><br>**@PathParam projectsid**: Identificador de instancia de Project||Lista de registros de projects(Project)
**POST**|projectSponsors/*:projectSponsorsid*/projects/*:projectsid*|Asocia una instancia de Project a una de ProjectSponsor|**@PathParam projectSponsorsid**: Identificador de instancia de ProjectSponsor<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Registro de projects(Project) para asociar a ProjectSponsor|Instancia de projects(Project) asociada a instancia de ProjectSponsor
**PUT**|projectSponsors/*:projectSponsorsid*/projects|Actualización de instancias de projects (Project) asociadas a ProjectSponsor|**@PathParam projectSponsorsid**: Identificador de instancia de ProjectSponsor|Colección de instancias de projects(Project) a actualizar|Colección de instancias de projects(Project) actualizados
**DELETE**|projectSponsors/*:projectSponsorsid*/projects/*:projectsid*|Remueve asociación de instancias de projects (Project) a ProjectSponsor|**@PathParam projectSponsorsid**: Identificador de instancia de ProjectSponsor<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Colección de atributo `id` de projects(Project) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Project
#### Estructura de objeto Project
```javascript
{
    category: '' /*Objeto que representa instancia de Category*/,
    startDate: '' /*Tipo Date*/,
    sponsor: '' /*Objeto que representa instancia de ProjectSponsor*/,
    deadLine: '' /*Tipo Date*/,
    description: '' /*Tipo String*/,
    status: '' /*Objeto que representa instancia de Status*/,
    publicationDate: '' /*Tipo Date*/,
    price: '' /*Tipo Integer*/,
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/projects|Lista los registros de Project (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Project y el total de registros en la base de datos en el header X-Total-Count
**GET**|/projects/*:projectsid*|Obtener los atributos de una instancia de Project (READ)|**@PathParam projectsid**: Identificador del registro||Atributos de la instancia de Project
**POST**|/projects|Crear una nueva instancia de la entidad Project (CREATE)||Atributos de la instancia de Project a crear|Instancia de Project creada, incluyendo su nuevo ID
**PUT**|/projects/*:projectsid*|Actualiza una instancia de la entidad Project (UPDATE)|**@PathParam projectsid**: Identificador del registro|Objeto JSON de Project|Instancia de Project actualizada
**DELETE**|/projects/*:projectsid*|Borra instancia de Project en el servidor (DELETE)|**@PathParam projectsid**: Identificador del registro||
**GET**|projects/*:projectsid*/expectedskills|Listar registros de expectedskills (Skill) asociados a Project|**@PathParam projectsid**: Identificador de instancia de Project||Colección de objetos JSON de expectedskills(Skill)
**GET**|projects/*:projectsid*/expectedskills/*:expectedskillsid*|Obtener un registro de expectedskills (Skill) asociado a Project|**@PathParam projectsid**: Identificador de instancia de Project<br><br>**@PathParam expectedskillsid**: Identificador de instancia de Skill||Lista de registros de expectedskills(Skill)
**POST**|projects/*:projectsid*/expectedskills/*:expectedskillsid*|Asocia una instancia de Skill a una de Project|**@PathParam projectsid**: Identificador de instancia de Project<br><br>**@PathParam expectedskillsid**: Identificador de instancia de Skill|Registro de expectedskills(Skill) para asociar a Project|Instancia de expectedskills(Skill) asociada a instancia de Project
**PUT**|projects/*:projectsid*/expectedskills|Actualización de instancias de expectedskills (Skill) asociadas a Project|**@PathParam projectsid**: Identificador de instancia de Project|Colección de instancias de expectedskills(Skill) a actualizar|Colección de instancias de expectedskills(Skill) actualizados
**DELETE**|projects/*:projectsid*/expectedskills/*:expectedskillsid*|Remueve asociación de instancias de expectedskills (Skill) a Project|**@PathParam projectsid**: Identificador de instancia de Project<br><br>**@PathParam expectedskillsid**: Identificador de instancia de Skill|Colección de atributo `id` de expectedskills(Skill) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Skill
#### Estructura de objeto Skill
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/skills|Lista los registros de Skill (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Skill y el total de registros en la base de datos en el header X-Total-Count
**GET**|/skills/*:skillsid*|Obtener los atributos de una instancia de Skill (READ)|**@PathParam skillsid**: Identificador del registro||Atributos de la instancia de Skill
**POST**|/skills|Crear una nueva instancia de la entidad Skill (CREATE)||Atributos de la instancia de Skill a crear|Instancia de Skill creada, incluyendo su nuevo ID
**PUT**|/skills/*:skillsid*|Actualiza una instancia de la entidad Skill (UPDATE)|**@PathParam skillsid**: Identificador del registro|Objeto JSON de Skill|Instancia de Skill actualizada
**DELETE**|/skills/*:skillsid*|Borra instancia de Skill en el servidor (DELETE)|**@PathParam skillsid**: Identificador del registro||
**GET**|skills/*:skillsid*/freelancers|Listar registros de freelancers (Freelancer) asociados a Skill|**@PathParam skillsid**: Identificador de instancia de Skill||Colección de objetos JSON de freelancers(Freelancer)
**GET**|skills/*:skillsid*/freelancers/*:freelancersid*|Obtener un registro de freelancers (Freelancer) asociado a Skill|**@PathParam skillsid**: Identificador de instancia de Skill<br><br>**@PathParam freelancersid**: Identificador de instancia de Freelancer||Lista de registros de freelancers(Freelancer)
**POST**|skills/*:skillsid*/freelancers/*:freelancersid*|Asocia una instancia de Freelancer a una de Skill|**@PathParam skillsid**: Identificador de instancia de Skill<br><br>**@PathParam freelancersid**: Identificador de instancia de Freelancer|Registro de freelancers(Freelancer) para asociar a Skill|Instancia de freelancers(Freelancer) asociada a instancia de Skill
**PUT**|skills/*:skillsid*/freelancers|Actualización de instancias de freelancers (Freelancer) asociadas a Skill|**@PathParam skillsid**: Identificador de instancia de Skill|Colección de instancias de freelancers(Freelancer) a actualizar|Colección de instancias de freelancers(Freelancer) actualizados
**DELETE**|skills/*:skillsid*/freelancers/*:freelancersid*|Remueve asociación de instancias de freelancers (Freelancer) a Skill|**@PathParam skillsid**: Identificador de instancia de Skill<br><br>**@PathParam freelancersid**: Identificador de instancia de Freelancer|Colección de atributo `id` de freelancers(Freelancer) a eliminar|
**GET**|skills/*:skillsid*/projects|Listar registros de projects (Project) asociados a Skill|**@PathParam skillsid**: Identificador de instancia de Skill||Colección de objetos JSON de projects(Project)
**GET**|skills/*:skillsid*/projects/*:projectsid*|Obtener un registro de projects (Project) asociado a Skill|**@PathParam skillsid**: Identificador de instancia de Skill<br><br>**@PathParam projectsid**: Identificador de instancia de Project||Lista de registros de projects(Project)
**POST**|skills/*:skillsid*/projects/*:projectsid*|Asocia una instancia de Project a una de Skill|**@PathParam skillsid**: Identificador de instancia de Skill<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Registro de projects(Project) para asociar a Skill|Instancia de projects(Project) asociada a instancia de Skill
**PUT**|skills/*:skillsid*/projects|Actualización de instancias de projects (Project) asociadas a Skill|**@PathParam skillsid**: Identificador de instancia de Skill|Colección de instancias de projects(Project) a actualizar|Colección de instancias de projects(Project) actualizados
**DELETE**|skills/*:skillsid*/projects/*:projectsid*|Remueve asociación de instancias de projects (Project) a Skill|**@PathParam skillsid**: Identificador de instancia de Skill<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Colección de atributo `id` de projects(Project) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Category
#### Estructura de objeto Category
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/categorys|Lista los registros de Category (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Category y el total de registros en la base de datos en el header X-Total-Count
**GET**|/categorys/*:categorysid*|Obtener los atributos de una instancia de Category (READ)|**@PathParam categorysid**: Identificador del registro||Atributos de la instancia de Category
**POST**|/categorys|Crear una nueva instancia de la entidad Category (CREATE)||Atributos de la instancia de Category a crear|Instancia de Category creada, incluyendo su nuevo ID
**PUT**|/categorys/*:categorysid*|Actualiza una instancia de la entidad Category (UPDATE)|**@PathParam categorysid**: Identificador del registro|Objeto JSON de Category|Instancia de Category actualizada
**DELETE**|/categorys/*:categorysid*|Borra instancia de Category en el servidor (DELETE)|**@PathParam categorysid**: Identificador del registro||
**GET**|categorys/*:categorysid*/projects|Listar registros de projects (Project) asociados a Category|**@PathParam categorysid**: Identificador de instancia de Category||Colección de objetos JSON de projects(Project)
**GET**|categorys/*:categorysid*/projects/*:projectsid*|Obtener un registro de projects (Project) asociado a Category|**@PathParam categorysid**: Identificador de instancia de Category<br><br>**@PathParam projectsid**: Identificador de instancia de Project||Lista de registros de projects(Project)
**POST**|categorys/*:categorysid*/projects/*:projectsid*|Asocia una instancia de Project a una de Category|**@PathParam categorysid**: Identificador de instancia de Category<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Registro de projects(Project) para asociar a Category|Instancia de projects(Project) asociada a instancia de Category
**PUT**|categorys/*:categorysid*/projects|Actualización de instancias de projects (Project) asociadas a Category|**@PathParam categorysid**: Identificador de instancia de Category|Colección de instancias de projects(Project) a actualizar|Colección de instancias de projects(Project) actualizados
**DELETE**|categorys/*:categorysid*/projects/*:projectsid*|Remueve asociación de instancias de projects (Project) a Category|**@PathParam categorysid**: Identificador de instancia de Category<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Colección de atributo `id` de projects(Project) a eliminar|

[Volver arriba](#tabla-de-contenidos)

### Entidad Education
#### Estructura de objeto Education
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/,
    startDate: '' /*Tipo Date*/,
    endDate: '' /*Tipo Date*/,
    institution: '' /*Tipo String*/,
    title: '' /*Tipo String*/,
    description: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/educations|Lista los registros de Education (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Education y el total de registros en la base de datos en el header X-Total-Count
**GET**|/educations/*:educationsid*|Obtener los atributos de una instancia de Education (READ)|**@PathParam educationsid**: Identificador del registro||Atributos de la instancia de Education
**POST**|/educations|Crear una nueva instancia de la entidad Education (CREATE)||Atributos de la instancia de Education a crear|Instancia de Education creada, incluyendo su nuevo ID
**PUT**|/educations/*:educationsid*|Actualiza una instancia de la entidad Education (UPDATE)|**@PathParam educationsid**: Identificador del registro|Objeto JSON de Education|Instancia de Education actualizada
**DELETE**|/educations/*:educationsid*|Borra instancia de Education en el servidor (DELETE)|**@PathParam educationsid**: Identificador del registro||

[Volver arriba](#tabla-de-contenidos)

### Entidad Status
#### Estructura de objeto Status
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```
#### Servicios
Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/statuss|Lista los registros de Status (READ)|**@QueryParam page**: página a consultar<br>**@QueryParam maxRecords**: cantidad de registros a consultar<br><br>*Si se omite alguno de estos parámetros se obtiene todos los registros en la base de datos*||Colección de registros de Status y el total de registros en la base de datos en el header X-Total-Count
**GET**|/statuss/*:statussid*|Obtener los atributos de una instancia de Status (READ)|**@PathParam statussid**: Identificador del registro||Atributos de la instancia de Status
**POST**|/statuss|Crear una nueva instancia de la entidad Status (CREATE)||Atributos de la instancia de Status a crear|Instancia de Status creada, incluyendo su nuevo ID
**PUT**|/statuss/*:statussid*|Actualiza una instancia de la entidad Status (UPDATE)|**@PathParam statussid**: Identificador del registro|Objeto JSON de Status|Instancia de Status actualizada
**DELETE**|/statuss/*:statussid*|Borra instancia de Status en el servidor (DELETE)|**@PathParam statussid**: Identificador del registro||
**GET**|statuss/*:statussid*/projects|Listar registros de projects (Project) asociados a Status|**@PathParam statussid**: Identificador de instancia de Status||Colección de objetos JSON de projects(Project)
**GET**|statuss/*:statussid*/projects/*:projectsid*|Obtener un registro de projects (Project) asociado a Status|**@PathParam statussid**: Identificador de instancia de Status<br><br>**@PathParam projectsid**: Identificador de instancia de Project||Lista de registros de projects(Project)
**POST**|statuss/*:statussid*/projects/*:projectsid*|Asocia una instancia de Project a una de Status|**@PathParam statussid**: Identificador de instancia de Status<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Registro de projects(Project) para asociar a Status|Instancia de projects(Project) asociada a instancia de Status
**PUT**|statuss/*:statussid*/projects|Actualización de instancias de projects (Project) asociadas a Status|**@PathParam statussid**: Identificador de instancia de Status|Colección de instancias de projects(Project) a actualizar|Colección de instancias de projects(Project) actualizados
**DELETE**|statuss/*:statussid*/projects/*:projectsid*|Remueve asociación de instancias de projects (Project) a Status|**@PathParam statussid**: Identificador de instancia de Status<br><br>**@PathParam projectsid**: Identificador de instancia de Project|Colección de atributo `id` de projects(Project) a eliminar|

[Volver arriba](#tabla-de-contenidos)

