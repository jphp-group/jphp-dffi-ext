package org.develnext.jphp.ext.system.classes;

import org.develnext.jphp.ext.system.DFFIExtension;
import php.runtime.annotation.Reflection;
import php.runtime.lang.BaseObject;
import php.runtime.env.Environment;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("DFFIType")
@Reflection.Namespace(DFFIExtension.NS)
public class DFFIType extends BaseObject {
	public static final String BOOL = "BOOL";
	public static final String INT = "INT";
	public static final String BYTE = "BYTE";
	public static final String LONG = "LONG";
	public static final String CHAR = "CHAR";
	public static final String DOUBLE = "DOUBLE";
	public static final String FLOAT = "FLOAT";
	public static final String STRING = "STRING";
	public static final String WIDESTRING = "WSTRING";
	public static final String STRUCT = "STRUCT";
	public static final String REFERENCE = "REF";
	
	public DFFIType(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }
}