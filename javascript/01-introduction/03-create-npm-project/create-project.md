# Crear un proyecto npm en JavaScript

Guía rápida con los comandos para crear un módulo. Un proyecto npm por módulo y un archivo por ejercicio.

## Crear el módulo

Desde la carpeta del módulo (ya existe en el repositorio):

```bash
cd javascript/02-fundamentals
npm init -y
```
init => crea el `package.json` del módulo
-y => acepta los valores por defecto, sin preguntas

Esto genera `package.json`.

## Crear un ejercicio

La carpeta del ejercicio ya existe; solo hay que crear el archivo:

```bash
touch 05-variables/index.js
```
touch => crea un archivo vacío

En PowerShell:

```powershell
New-Item 05-variables/index.js
```

Si la carpeta no existe, crearla antes con `mkdir 05-variables`.

Contenido mínimo de `index.js` (equivalente al `Program.cs` generado por `dotnet new console`):

```javascript
console.log("Hello, World!");
```

## Ejecutar

```bash
node 05-variables/index.js
```

## Otros comandos útiles

```bash
npm pkg set type=module    # usar import/export en lugar de require
npm install <paquete>      # añadir una dependencia
node --version
```

`package.json` y `package-lock.json` se suben al repositorio. `node_modules/` se genera con `npm install` y está en `.gitignore`.
