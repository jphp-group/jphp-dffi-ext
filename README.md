# DFFI
DevelNext Foreign Function Interface


## Example

```php
<?php
use system\DFFI;
use system\DFFIType;

$user32 = new DFFI("user32");
$user32->bind("MessageBoxA", DFFIType::INT, [DFFIType::INT, DFFIType::STRING, DFFIType::STRING, DFFIType::INT]);
//int MesssageBoxA(int, string, string, int)

DFFI::MessageBoxA(0, "Hello", "HelloWorld", 0);
```

## API Documentation
[api-docs](api-docs/)

## Bundle for DevelNext
[Download](https://github.com/jphp-group/jphp-dffi-ext/releases/download/1.0.0/dn-dffi-bundle.dnbundle)
