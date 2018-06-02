# DFFI

- **class** `DFFI` (`system\DFFI`)
- **source** `system/DFFI.php`

**Description**

Class DFFI

---

#### Methods

- `->`[`__construct()`](#method-__construct) - _DFFI constructor._
- `->`[`bind()`](#method-bind) - _Bind function_
- `->`[`addSearchPath()`](#method-addsearchpath) - _Add library to search path_
- `->`[`getJFXHandle()`](#method-getjfxhandle) - _Get JavaFX window handle_

---
# Methods

<a name="method-__construct"></a>

### __construct()
```php
__construct(string $lib): void
```
DFFI constructor.

---

<a name="method-bind"></a>

### bind()
```php
bind(string $functionName, string $returnType, array $types): void
```
Bind function

---

<a name="method-addsearchpath"></a>

### addSearchPath()
```php
addSearchPath(string $lib, string $path): void
```
Add library to search path

---

<a name="method-getjfxhandle"></a>

### getJFXHandle()
```php
getJFXHandle(UXForm $form): void
```
Get JavaFX window handle